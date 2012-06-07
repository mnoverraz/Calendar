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
	
	//appel fonction dans contr�leur
	
    //fullcalendar
	//var date = new Date();
	/*var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();*/
	calendar = $('#calendar').fullCalendar({
		header : {
			left : 'prev,next today, next-year',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
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
		timeFormat :{
		    // for agendaWeek and agendaDay
		    agenda: 'H:mm{ - H:mm}',
		    '': 'H:mm'
		},
		titleFormat :{
		    month: 'MMMM yyyy',
		    week: "d MMM yyyy - {dd MMM yyyy}",
		    day: 'dddd d MMMM yyyy'
		},
		columnFormat :{
		    month: 'ddd',    // Mon
		    week: 'ddd d mm', // Mon 9/7
		    day: 'dddd d MM'  // Monday 9/7
		},
		axisFormat : 'HH:mm',
		slotMinutes : 30,
		firstDay : 1,
		firstHour : 7,
		editable : true,
		//Language options
		monthNames : eval(resourceBundle['month-names']),
		monthAbbrevs : eval(resourceBundle['month-names-short']),
		dayNames : eval(resourceBundle['day-names']),
		dayNamesShort : eval(resourceBundle['day-names-short']),
		//defaultView: 'agendaWeek',
		//events: 'rest/event/?example&showState=false',
		events: function(start, end, addEvents) {
	        $.ajax({
	            url: 'rest/event/?example&showState=false',
	            dataType: 'json',
	            data: {
	                // our hypothetical feed requires UNIX timestamps
	                start: Math.round(start.getTime() / 1000),
	                end: Math.round(end.getTime() / 1000)
	            },
	            success: function(doc) {
	                var events = [];
	                $(doc).find('event').each(function() {
	                    events.push({
	                        title: $(this).attr('title'),
	                        start: $(this).attr('start'), // will be parsed
	                        end: $(this).attr('end'),
	                        id: $(this).attr('id'),
	                        allDay: $(this).attr('allDay')
	                    });
	                });
	                addEvents(doc);
	            }
	        });
	    },
	    
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			    buttonOpts = {};
			    buttonOpts['Fermer'] = $.extend(function() {
			        $(this).dialog("close");
			    },{
			        id : 'monId'
			    });
			    buttonOpts['Valider'] = $.extend(function() {                    
			    	send('rest/event/?example', $('eventform').serialize(),'post');
			    }, {
			        id : 'delete'
			    });
			  
		        showDialog('eventdialog.jsp', 'Editer évenement', buttonOpts);
		        
		        $("#edate").val('too');
		        $("#repeat_end").val('toto');
		        
			calendar.fullCalendar('unselect');
		},
		eventClick: function(calEvent, jsEvent, view) {
	    	alert('Event: ' + calEvent.title);
	    	alert('time start: ' + calEvent.start);
	    	alert('time end: ' + calEvent.end);
	        alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
	        alert('View: ' + view.name);

	        // change the border color just for fun
	        $(this).css('border-color', 'red');

	    },
	    eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {

	        alert(
	            event.title + " was moved " +
	            dayDelta + " days and " +
	            minuteDelta + " minutes."
	        );

	        if (allDay) {
	            alert("Event is now all-day");
	        }else{
	            alert("Event has a time-of-day");
	        }

	        if (!confirm("Are you sure about this change?")) {
	            revertFunc();
	        }

	    },
		eventResize: function(event,dayDelta,minuteDelta,revertFunc) {

	        alert(
	            "The end date of " + event.title + "has been moved " +
	            dayDelta + " days and " +
	            minuteDelta + " minutes."
	        );

	        if (!confirm("is this okay?")) {
	            revertFunc();
	        }

	    }
	});
	
	
});