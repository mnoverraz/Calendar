function send(url, method) {
	$.ajax({
		type : method,
		url : url,
		dataType: "json",
		data : {
			example : ""
		},
		success : function(msg) {
			getMessage(msg);
		}
	});	
}
//foreach $.each(errors, function(key, val) {

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
            send('rest/event/?example','get');
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
			addEvents(json);
		//}
		
	}else{
		processError(json);
	}
	

	//calendar.fullCalendar( 'renderEvent', eric);
}

function addEvents(json){
	
	this.ev = {
		    "success": true,
		    "content": [
		        {
		            "id": "1",
		            "title": "event 1 (récurrent)",
		            "allDay": false,
		            "start": "2012-06-15 08:00",
		            "end": "2012-06-15 10:00"
		        },
		        {
		            "id": "1",
		            "title": "event 1 (récurrent)",
		            "allDay": true,
		            "start": "2012-06-02 22:13",
		            "end": "2012-06-02 22:13"
		        },
		        {
		            "id": "2",
		            "title": "event 2",
		            "allDay": false,
		            "start": "2012-06-16 11:00",
		            "end": "2012-06-16 13:00"
		        },
		        {
		            "id": "3",
		            "title": "event 3",
		            "allDay": false,
		            "start": "2012-06-05 22:00",
		            "end": "2012-06-05 23:00"
		        },
		        {
		            "id": "3",
		            "title": "event 3",
		            "allDay": false,
		            "start": "2012-06-07 22:30",
		            "end": "2012-06-07 23:45"
		        }
		    ]
		};
	
	
	for(var i= 0; i < ev['content'].length; i++)
	{
	     calendar.fullCalendar('renderEvent', ev['content'][i], true);
	}
	//calendar.fullCalendar('renderEvent', ev , true);
	
}

function processError(error){
	alert('processError');
}

