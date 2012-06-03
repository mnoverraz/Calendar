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
	
	//appel fonction dans contrôleur
	
    //fullcalendar
	//var date = new Date();
	/*var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();*/
	calendar = $('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		editable : true,
		//defaultView: 'agendaWeek',
		events: 'rest/event/?example&showState=false',
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			    buttonOpts = {};
			    buttonOpts['Fermer'] = $.extend(function() {
			        $(this).dialog("close");
			    },{
			        id : 'monId'
			    });
			    buttonOpts['Button 2'] = $.extend(function() {                    
			    	//action à faire
			    }, {
			        id : 'delete'
			    });
			    
				
		        showDialog('eventdialog.jsp', 'Editer évenement', buttonOpts);
		        
			calendar.fullCalendar('unselect');
		},
	});
	
	
	
});