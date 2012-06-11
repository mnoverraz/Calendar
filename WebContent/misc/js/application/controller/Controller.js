function send(url, data, method) {
	console.log(method);
	//if(method == 'put'){
		$.ajax({
			type : method,
			url : url,
			dataType: "json",
			data : data,
			success : function(msg) {
				getMessage(msg);
				$('.ui-dialog').unblock();
				if (msg["success"]) {
	                $('#dialog').dialog("close");
	            }
			}
		});
		
	//}
	
	
	/*$.ajax({
		type : method,
		url : url,
		dataType: "json",
		data : data,
		success : function(msg) {
			getMessage(msg);
			$('.ui-dialog').unblock();
			if (msg["success"]) {
                $('#dialog').dialog("close");
            }
		}
	});*/
}

/*
 * 
 * 		{
			example : ""
		}
	
 * 
 */
//foreach $.each(errors, function(key, val) {


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
			dialogTitle = 'Nom de l événement';
			data = {
					id : event['id']
			};
				
			buttonOpts['Supprimer'] = $.extend(function() {                    
			    	send('rest/event/', data,'delete');
			}, {
			        id : 'delete'
			});
			showDialog(url, event, buttonOpts);
			break;
		case 'create':
			console.log(mode);
			dialogTitle = 'Création événement';
			data = $('#eventform').serialize();
			 buttonOpts['Créer'] = $.extend(function() {
				 sendForm('rest/event/', data,'put');
			    },{
			        id : 'create'
			    });
			showDialog(url, null, buttonOpts);
			break;
		case 'update':
			dialogTitle = 'Modifier événement';
			data = {
					method : 'post'
				};
			buttonOpts['Modifier'] = $.extend(function() {                    
		    	sendForm('rest/event/', data,'post');
		    }, {
		        method : 'post'
		    });
			showDialog(url, event, buttonOpts);
			break;
		case 'delete':
			dialogTitle = 'Suppression événement';
			data = {
				id : event['id']
			};
			
			buttonOpts['Supprimer'] = $.extend(function() {                    
		    	send('rest/event/', data,'delete');
		    }, {
		        id : 'delete'
		    });
			break;
	
	}
	
}

function showDialog(url, event, buttonOpts) {
	var $dialog = $('<div id=\"dialog\"></div>')
    .load(url)
    .dialog({
        title: 'titre',
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


    /*if (eventMode != 'edit') {
        $("#delete").hide();
    }*/
    return false;
}

function getMessage(json){	
	
	if(json['success']){
		if(json['content'] == ''){
			//success mais rien n'arrive
			//soit la création ok soit dataset vide
			alert('success mais retour vide');
		}else{
			alert(json['content']);
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
	alert(error['content'][0]);
}

