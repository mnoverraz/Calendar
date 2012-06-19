/**
 * This script is a listener for all eventdialog events
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
/**
 * Show or hide the repeatSelector for an event
 */
function repeatSelector() {
    if ($("#repeatMode :selected").val() == 'n') {
        $("#repeat_date").hide();
        $("#repeatEnd").removeClass('required');
    }
    else {
        $("#repeat_date").show();
        $("#repeatEnd").addClass('required');
    }
}

$(document).ready(function() {
    $('#title').focus();
    /**
     * Fill the date input with the current date
     */
    if(typeof(eventData)!='undefined'){
    	fillEvent();
	}
    if ($("#date").val() == '') {
        $("#date").val(eventDate);
    }
    /**
     * Check the date
     */
    if ($("#repeatEnd").val() == '') {
        $("#repeatEnd").val(repeatEnd);
    }
    
    $("#date").change(function() {
        eventDate = $("#date").val();
        repeatEnd = $("#repeatEnd").val();
        
        if (eventDate > repeatEnd) {
        	$("#repeatEnd").val(eventDate);
        }
        
    });
    /**
     * Check time
     */
    $("#startH").change(function() {
        startHour = $("#startH").val();
		startHour = parseInt(startHour);
		endHour = 23;

        if (startHour < 23) {
            endHour =  startHour + 1;
            if (endHour < 10) {
                endHour = '0' + endHour;
            }
        }
		$("#endH").val(endHour);
    });
	/**
	 * Date Picker
	 */
    $(".datepicker").datepicker({
        dayNamesMin: eval(resourceBundle['day-names-short']),
        monthNames: eval(resourceBundle['month-names']),
        monthNamesShort: eval(resourceBundle['month-names-short']),
        firstDay: 1,
        maxDate: (new Date().getFullYear() + 1) + '-12-31',
        minDate: new Date(),
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
		changeYear: true
    });
    $('#allDay').change(function() {
        wholeDay();
    });
    repeatSelector();
    $('#repeatMode').change(function() {
        repeatSelector();
    });
    
    wholeDay();
});
/**
 * Show or hide the wholeDay option
 */
function wholeDay() {
	if ($("#allDay").is(":checked")) {
		$("#start").hide();
		$("#startH option[text=00]").attr("selected", true);
		$("#startM option[text=00]").attr("selected", true);
		$("#end").hide();
		$("#endH option[text=00]").attr("selected", true);
		$("#endM option[text=00]").attr("selected", true);
	} else {
		$("#start").show();
		$("#end").show();
	}
}
/**
 * Send the form (serialized) from the dialogEvent.
 */
function sendForm(url, data, method) {
    dataString = $('#eventform').serialize();
    send(url, dataString, method);
}
/**
 * Fill the event content in the dialog fields
 */
function fillEvent(){	
	if(eventData['id'] != undefined){
		$("#id").val(eventData['id']);
	}
	if(eventData['title'] != undefined){
		$("#title").val(eventData['title']);
	}
	if(eventData['start'] != undefined){
		$("#date").val(dateToString(eventData['start']));

		if(eventData['start'].getHours() == 0 && eventData['start'].getMinutes() == 0){
			$("#startH").val(new Date().getHours());
			$("#startM").val(new Date().getMinutes());
			$("#endH").val(new Date().getHours());
			$("#endM").val(new Date().getMinutes());
		}else{
			$("#startH").val(intOn2Digit(eventData['start'].getHours()));
			$("#startM").val(intOn2Digit(eventData['start'].getMinutes()));
			if(eventData['end'] != undefined){
				$("#endH").val(intOn2Digit(eventData['end'].getHours()));
				$("#endM").val(intOn2Digit(eventData['end'].getMinutes()));
			}

		}
	}

	if(eventData['repeatMode'] != undefined){
		$("#repeatMode").attr('value',eventData['repeatMode']);
	}

	if(eventData['repeatEnd'] != undefined){
		if (eventData['repeatEnd'] != '')
			$("#repeatEnd").val(eventData['repeatEnd']);
	}

	if(eventData['description'] != undefined){
		$("#description").val(eventData['description']);
	}
	if(eventData['allDay'] != undefined){
		if(eventData['allDay']){
			$('#allDay').attr("checked", true);
		}
	}
	eventData = null;

}