function send(url, data, method) {
	$.ajax({
		type : method,
		url : url,
		dataType: "json",
		data : data,
		success : function(msg) {
			getMessage(msg);
		}
	});	
}

/*
 * 
 * 		{
			example : ""
		}
	
 * 
 */
//foreach $.each(errors, function(key, val) {

toto = {
		example : ""
};


function showDialogEvent(url, mode){
	
	//Definitions
	dialogTitle = null;
	buttonOpts = {};
    buttonOpts['Fermer'] = $.extend(function() {
        $(this).dialog("close");
    },{
        id : 'close'
    });
    
	
	switch(mode){
		case 'consult':
			dialogTitle = 'Nom de l événement';
			data = {
					example : ""
				};
			break;
		case 'create':
			dialogTitle = 'Création événement';
			data = {
					example : "id: 1"
				};
			buttonOpts['Créer'] = $.extend(function() {                    
		    	send('rest/event/?example', $('eventform').serialize(),'post');
		    	alert($('eventform').serialize());
		    }, {
		        id : 'update'
		    });
			break;
		case 'update':
			dialogTitle = 'Modifier événement';
			data = {
					example : "id: 1"
				};
			buttonOpts['Modifier'] = $.extend(function() {                    
		    	send('rest/event/?example', $('eventform').serialize(),'put');
		    }, {
		        id : 'update'
		    });
			break;
		case 'delete':
			dialogTitle = 'Suppression événement';
			data = {
				example : "id: 1"
			};
			
			buttonOpts['Supprimer'] = $.extend(function() {                    
		    	send('rest/event/?example', data,'delete');
		    }, {
		        id : 'delete'
		    });
			break;
	
	}
	showDialog(url, dialogTitle, buttonOpts);
	
}

function showDialog(url, dialogTitle, buttonOpts) {
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
        position: 'center'
    });

    $dialog.dialog('open');
    alert($('eventform').serialize());

    /*if (eventMode != 'edit') {
        $("#delete").hide();
    }*/
    return false;
}

function getMessage(json){
	this.toto = json;
	
	
	if(json['success']){
		if(json['content'] == ''){
			alert('empty');
		}else{
			addEvents(json);
		}
		
	}else{
		processError(json);
	}

}

function addEvents(json){
	alert('ok');
	for(var i= 0; i < json['content'].length; i++)
	{
	     calendar.fullCalendar('renderEvent', json['content'][i], true);
	}
	
}

function processError(error){
	alert('processError');
}

