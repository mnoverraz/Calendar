function send(url, method) {
	$.ajax({
		type : method,
		url : url,
		dataType: "text",
		data : {
			example : ""
		},
		success : function(msg) {
			$('#log').append("<br/>method: " + method);
			$('#log').append("<br/>result" + msg);
			$('#log').append("<br/>--------------");
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