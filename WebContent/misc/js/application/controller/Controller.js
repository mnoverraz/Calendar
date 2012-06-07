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

    /*if (eventMode != 'edit') {
        $("#delete").hide();
    }*/
    return false;
}

function getMessage(json){
	this.toto = json;
	
	
	if(json['success']){
		/*if(json['content'] == ''){
			alert('empty');
		}else{*/
			//addEvents(json);
		//}
		
	}else{
		processError(json);
	}
	

	//calendar.fullCalendar( 'renderEvent', eric);
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

