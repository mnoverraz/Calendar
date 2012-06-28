/**
 * JavaScript controller
 * Generic type is resolved to EventController
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */

loadRooms();

function send(url, data, method) {
	$.ajax({
		type : method,
		url : url,
		dataType: 'json',
		data : data,
		success : function(msg){
			$('.ui-dialog').unblock();
			if (msg['success']) {
				$('#dialog').dialog('close');
				calendar.fullCalendar('refetchEvents');
			}
			else 
				processError(msg);
		}
	});
	
}
/**
 * Fill rooms fields in HTML page
 */
function fillRooms(json){
	$("#room_description").html(json.content[0].description);
	$("#room_local").html(json.content[0].local);
	//$("#room_category").html(json.content[0].roomCategory);
	$("#room_name").html(json.content[0].name);
}
/**
 * Load rooms informations
 */
function loadRooms(){
	$.ajax({
		type : 'get',
		url : 'rest/room/?id=1',
		dataType: 'json',
		data : null,
		success : function(msg) {
			fillRooms(msg);
		}
	});
}
/**
 * Preapare dialog content
 */
function showDialogEvent(url, mode, event){
	//Definitions
	this.eventData = event;
	buttonOpts = {};
    buttonOpts[resourceBundle['calendar-event-cancel']] = $.extend(function() {
        $(this).dialog("close");
    },{
        id : 'close'
    });
    
	
	switch(mode){
		case 'consult':
			dialogTitle = event.title;
			data = {
					id : event['id']
			};
				
			buttonOpts[resourceBundle['calendar-event-delete']] = $.extend(function() {                    
			    	send('rest/event/', data,'delete');
			}, {
			        id : 'delete'
			});
			buttonOpts[resourceBundle['calendar-event-edit']] = $.extend(function() {                    
		    	sendForm('rest/event/', data,'post');
		    }, {
		        method : 'post'
		    });
			showDialog(url, event, dialogTitle, buttonOpts);
			break;
		case 'create':
			dialogTitle = resourceBundle['calendar-event-new'];
			data = $('#eventform').serialize();
			 buttonOpts[resourceBundle['calendar-event-save']] = $.extend(function() {
				 sendForm('rest/event/', data,'put');
			    },{
			        id : 'create'
			    });
			showDialog(url, null, dialogTitle, buttonOpts);
			break;
		case 'update':
			send('rest/event/', event,'post');
			break;
		case 'delete':
			dialogTitle = 'Suppression événement';
			data = $('#eventform').serialize();
			
			buttonOpts[resourceBundle['calendar-event-delete']] = $.extend(function() {                    
				 sendForm('rest/event/', data,'delete');
		    }, {
		        id : 'delete'
		    });
			break;
		case 'error':
			dialogTitle = 'Erreur';
			showDialog(url, null, dialogTitle, buttonOpts);
			break;
	
	}
	
}
/**
 * Show the dialog
 */
function showDialog(url, event, dialogTitle, buttonOpts) {
	var $dialog = $('<div id=\"dialog\"></div>')
    .load(url)
    .dialog({
        title: dialogTitle,
        autoOpen: false,
        width: 290,
        buttons: buttonOpts,
        close: function(ev, ui) {
            $(this).remove();
            $('#dialog-confirm').remove();
        },
        resizable: false,
        modal: true,
        closeOnEscape: true,
        position: 'center',
        
    });

    $dialog.dialog('open');
    return false;
}
/**
 * Get an json error message and show an
 * error dialog
 */
function processError(error){
	//TimeSlotException
	if(error.content[0].TimeSlotException != undefined){
		
		errorDialog(exceptionToUI('TimeSlotException', error.content[0].TimeSlotException), 'TimeSlotException');
	}
}
/**
 * Show an error dialog
 */
function errorDialog(message, errorName) {
	$("#dialog-error").html(message);
	buttonsOpts = {};
    buttonsOpts[resourceBundle['calendar-event-cancel']] = function() {
        $(this).dialog( "close" );
    };
    $("#dialog-error").dialog({
    	title: errorName,
        resizable: false,
        width: 350,
        modal: true,
        buttons: buttonsOpts
    });
}
/**
 * Format the error to HTML
 */
function exceptionToUI(exceptionType, detail) {
	out = "";
	switch(exceptionType) {
    	case 'TimeSlotException' :
			out += "<ul>";
			$.each(detail, function(k, v) {
				out += "<li>";
				out += v["start"] + " " + v["end"];
				out += "</li>";
			});
			out += "</ul>";
			break;
    	default :
    		out += "Other exception";
    		break;
	}
	return out;
}

