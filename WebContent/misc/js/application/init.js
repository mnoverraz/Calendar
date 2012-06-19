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
			    month: resourceBundle['format-title-month'],
			    week: resourceBundle['format-title-week'],
			    day: resourceBundle['format-title-day']
			},
			columnFormat :{
			    month: resourceBundle['format-column-month'],
			    week: resourceBundle['format-column-week'],
			    day: resourceBundle['format-column-day']
			},
			axisFormat : 'HH:mm',
			slotMinutes : 30,
			firstDay : 1,
			firstHour : 7,
			editable : false,
			
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
			monthNamesShort : eval(resourceBundle['month-names-short']),
			dayNames : eval(resourceBundle['day-names']),
			dayNamesShort : eval(resourceBundle['day-names-short']),
			
		//---------
		//Actions
		//---------
			events: function(start, end, addEvents) {
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

		                $(doc).each(function(index, object) {
		                    var event = {
		                    	id: object.id,
		                        title: object.title,
		                        allDay: object.allDay,
		                        start: object.start,
		                        end: object.end,
		                        repeatEnd: object.repeatEnd,
		                        repeatMode: object.repeatMode,
		                        description: object.description
		                    };
		                    if (object.repeatMode != 'n') event.color = '#f15c2e';
		                    events.push(event);
		                });

		                addEvents(events);
		            }
		        });
		    },
		    
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) { //Trigger when sb click on a day
					eventData = {
						id: null,
						title: '',
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
		        showDialogEvent('eventdialog.jsp', 'update', event);
		    },
			eventResize: function(event,dayDelta,minuteDelta,revertFunc) { //Trigger when sb resize an event (only on weekView and dayView)
		        showDialogEvent('eventdialog.jsp', 'update', event);
		    }
	});
	
	
	
	
});