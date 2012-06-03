function send(url, method) {
	return $.ajax({
		type : method,
		url : url,
		dataType: "json",
		data : {
			example : ""
		},
		success : function(msg) {
			return msg;
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
            addEvents(send('rest/event/?example','get'));
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

function addEvents(json){
	
	eric = '{\'id\':\'9\',\'title\':\'reccurent 1\',\'allDay\':false,\'start\':\'2012-05-31 21:42\',\'end\':\'2012-05-31 23:00\'}';
	alert(eric);
	calendar.fullCalendar( 'renderEvent', eric);
}

