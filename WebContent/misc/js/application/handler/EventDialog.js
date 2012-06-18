/**
 * This script is a listener for all eventdialog events
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
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
    
    if(typeof(eventData)!='undefined'){
    	fillEvent();
	}
    if ($("#date").val() == '') {
        $("#date").val(eventDate);
    }

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

function sendForm(url, data, method) {
    dataString = $('#eventform').serialize();
    send(url, dataString, method);
}

function fillEvent(){	
	$("#id").val(eventData['id']);
	$("#title").val(eventData['title']);
	$("#date").val(dateToString(eventData['start']));
	
	if(eventData['start'].getHours() == 0 && eventData['start'].getMinutes() == 0){
		$("#startH").val(new Date().getHours());
		$("#startM").val(new Date().getMinutes());
		$("#endH").val(new Date().getHours());
		$("#endM").val(new Date().getMinutes());
		
	}else{
		
		$("#startH").val(intOn2Digit(eventData['start'].getHours()));
		$("#startM").val(intOn2Digit(eventData['start'].getMinutes()));
		$("#endH").val(intOn2Digit(eventData['end'].getHours()));
		$("#endM").val(intOn2Digit(eventData['end'].getMinutes()));
	}
	
	$("#repeatMode").attr('value',eventData['repeatMode']);
	if(eventData['repeatEnd'] != undefined){
		$("#repeatEnd").val(eventData['repeatEnd']);
	}
	$("#description").val(eventData['description']);
	
	if(eventData['allDay']){
		$('#allDay').attr('checked', true);
	}
	eventData = null;
}