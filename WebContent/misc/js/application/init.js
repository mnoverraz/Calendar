var calendar = null;

$(document).ready(function() {
	/*Fonction pour réécrire le buttonpane afin de pouvoir
     *définir l'id, la classe et le titre. Dévleoppée par
     * http://blog.mynotiz.de/programmieren/jquery-ui-dialog-buttons-anpassen-945/
     *
     *Utilisation:
     *
     *          "MonButton" : $.extend(function() {
                    actions à faire
                }, {
                    classes : 'maclasse',
                    title : 'montittre',
                    id : 'monid'
                }),
     */
    (function() {
        var dialogPrototype = $.ui.dialog.prototype;
        var originalButtons = dialogPrototype._createButtons;
        dialogPrototype._createButtons = function(buttons) {

            originalButtons.apply(this, arguments);

            var $buttons = this.element.siblings('.ui-dialog-buttonpane').find('button');

            var i = 0;
            for ( var label in buttons) {
                var button = buttons[label];
                var $button = $buttons.eq(i);

                if (button.title) {
                    $button.attr('title', button.title);
                }

                if (button.classes) {
                    $button.addClass(button.classes);
                }

                if (button.id) {
                    $button.attr('id', button.id);
                }

                i += 1;
            }
        };

    })();
	
	calendar = $('#calendar').fullCalendar({
		//--------------
		//Configuration
		//--------------
			header : {
				left : 'prev,next today, next-year',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			defaultView: 'month',
			timeFormat :{
			    // for agendaWeek and agendaDay
			    agenda: 'H:mm{ - H:mm}',
			    '': 'H:mm'
			},
			titleFormat :{
			    month: 'MMMM yyyy',
			    week: 'd MMM yyyy - {dd MMM yyyy}',
			    day: 'dddd d MMMM yyyy'
			},
			columnFormat :{
			    month: 'ddd',
			    week: 'ddd d MMM',
			    day: 'dddd d MMMM'
			},
			axisFormat : 'HH:mm',
			slotMinutes : 30,
			firstDay : 1,
			firstHour : 7,
			editable : true,
			
		//---------
		//Language
		//---------
			buttonText :{
			    prev:     '&nbsp;&#9668;&nbsp;',  // left triangle
			    next:     '&nbsp;&#9658;&nbsp;',  // right triangle
			    prevYear: '&nbsp;&lt;&lt;&nbsp;', // <<
			    nextYear: '&nbsp;&gt;&gt;&nbsp;', // >>
			    today:    resourceBundle['today'],
			    month:    resourceBundle['month'],
			    week:     resourceBundle['week'],
			    day:      resourceBundle['day']
			},
			monthNames : eval(resourceBundle['month-names']),
			monthAbbrevs : eval(resourceBundle['month-names-short']),
			dayNames : eval(resourceBundle['day-names']),
			dayNamesShort : eval(resourceBundle['day-names-short']),
			
		//---------
		//Actions
		//---------
			events: function(start, end, addEvents) {
				console.log('------------------');
				console.log('----Chargement des events-----');
				console.log('	start: ' + start);
				console.log('	end: ' + end);

		        $.ajax({
		            url: 'rest/event/?showState=false',
		            dataType: 'json',
		            data: {
		                // our hypothetical feed requires UNIX timestamps
		                /*start: Math.round(start.getTime() / 1000),
		                end: Math.round(end.getTime() / 1000)*/
		            },
		            success: function(doc) {
		                var events = [];
		                $(doc).find('event').each(function() {
		                    events.push({
		                    	id: $(this).attr('id'),
		                        title: $(this).attr('title'),
		                        allDay: $(this).attr('allDay'),
		                        start: $(this).attr('start'),
		                        end: $(this).attr('end'),
		                        description: $(this).attr('description'),
		                    });
		                });
		                addEvents(doc);
		            }
		        });
		    },
		    
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) { //Trigger when sb click on a day
					eventData = {
						id: null,
						title: ' ',
				        start: start,
				        end: end,
				        allDay: false
				    };
				
					showDialogEvent('eventdialog.jsp', 'create', eventData);

			        calendar.fullCalendar('unselect');
			},
			eventClick: function(calEvent, jsEvent, view) { //Trigger when sb click on the event

				showDialogEvent('eventdialog.jsp', 'consult', calEvent);
	
		    },
		    eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) { //Trigger when sb drop a event
		        
		    	console.log('------------------');
				console.log('----Update by eventDrop-----');
				console.log('	id: ' + event['id']);
				console.log('	title: ' + event['title']);
				console.log('	start: ' + event['start']);
				console.log('	end: ' + event['end']);
				console.log('	repeatMode: ' + event['repeatMode']);
				console.log('	description: ' + event['description']);
				console.log('------------------');
				
				
		        showDialogEvent('eventdialog.jsp', 'update', event);
	
		    },
			eventResize: function(event,dayDelta,minuteDelta,revertFunc) { //Trigger when sb resize an event (only on weekView and dayView)
				
				console.log('------------------');
				console.log('----Update by eventResize-----');
				console.log('	id: ' + event['id']);
				console.log('	title: ' + event['title']);
				console.log('	start: ' + event['start']);
				console.log('	end: ' + event['end']);
				console.log('	repeatMode: ' + event['repeatMode']);
				console.log('	description: ' + event['description']);
				console.log('------------------');
		        showDialogEvent('eventdialog.jsp', 'update', event);
	
		    }
	});
	
	
});