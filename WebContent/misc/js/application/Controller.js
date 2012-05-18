function get();
function post();
function put();
function remove();

function send(method) {
	$.ajax({
		type : method,
		url : "../rest/event/",
		dataType: "text",
		data : {
			example : ""
		},
		success : function(msg) {
			$('#log').append("<br/>method: " + method);
			$('#log').append("<br/>result" + msg);
			$('#log').append("<br/>--------------")
		}
	});
}
//foreach $.each(errors, function(key, val) {

function showDialog() {
	var $dialog = $('<div id=\"dialog\"></div>')
    .load('./php/views/eventdialog.php?mode=' + eventMode + '&posX=' + eventPosX + "&posY=" + eventPosY)
    .dialog({
        title: dialogTitle,
        autoOpen: false,
        width: 290,
        buttons: buttonsOpts,
        close: function(ev, ui) {
            calendar();
            $(this).remove();
            $('#dialog-confirm').remove();
        },
        resizable: false,
        modal: true,
        closeOnEscape: true,
        position: 'center'
    });

    $dialog.dialog('open');

    if (eventMode != 'edit') {
        $("#delete").hide();
    }
    return false;
}