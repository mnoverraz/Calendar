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
			getMessage(msg);
			$('.ui-dialog').unblock();
			if (msg['success']) {
				$('#dialog').dialog('close');
			}
		}
	});
	calendar.fullCalendar('refetchEvents');
}

function fillRooms(json){
	$("#room_description").html(json.content[0].description);
	$("#room_local").html(json.content[0].local);
	//$("#room_category").html(json.content[0].roomCategory);
	$("#room_name").html(json.content[0].name);
}

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

function showDialogEvent(url, mode, event){
	//Definitions
	this.eventData = event;
	buttonOpts = {};
    buttonOpts['Fermer'] = $.extend(function() {
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
				
			buttonOpts['Supprimer'] = $.extend(function() {                    
			    	send('rest/event/', data,'delete');
			}, {
			        id : 'delete'
			});
			buttonOpts['Modifier'] = $.extend(function() {                    
		    	sendForm('rest/event/', data,'post');
		    }, {
		        method : 'post'
		    });
			showDialog(url, event, dialogTitle, buttonOpts);
			break;
		case 'create':
			dialogTitle = 'Création événement';
			data = $('#eventform').serialize();
			 buttonOpts['Créer'] = $.extend(function() {
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
			
			buttonOpts['Supprimer'] = $.extend(function() {                    
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

function getMessage(json){	
	
	if(json['success']){
		if(json['content'] == ''){
			//success mais rien n'arrive
			//soit la création ok soit dataset vide
			alert('success mais retour vide');
		}else{
			addEvents(json);
		}
		
	}else{
		processError(json);
	}
}


function processError(error){
	//TimeSlotException
	if(error.content[0].TimeSlotException != undefined){
		
		errorDialog(exceptionToUI('TimeSlotException', error.content[0].TimeSlotException), 'TimeSlotException');
	}
}

function errorDialog(message, errorName) {
	$("#dialog").html(message);
	buttonsOpts = {};
    buttonsOpts["Fermer"] = function() {
        $(this).dialog( "close" );
    };
    $("#dialog").dialog({
    	title: errorName,
        resizable: false,
        width: 350,
        modal: true,
        buttons: buttonsOpts
    });
}

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

