/**
 * This script is a listener for all eventdialog events
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.18
 */


/**
 * Show or hide the repeatSelector for an event
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.18
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

    if ($("#repeatEnd").val() == '') {
        $("#repeatEnd").val(repeatEnd);
    }

    /**
     * Check the date
     */
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
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.18
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
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.18
 */
function sendForm(url, data, method) {
    dataString = $('#eventform').serialize();
    send(url, dataString, method);
}

/**
 * Fill the event content in the dialog fields
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.18
 */
function fillEvent(){
	
	console.log('----------fill event source ------------');
	console.log('id: ', eventData['id']);
	console.log('title: ', eventData['title']);
	console.log('date: ', eventData['start']);
	console.log('repeatMode: ', eventData['repeatMode']);
	console.log('repeatEnd: ', eventData['repeatEnd']);
	console.log('description: ', eventData['description']);
	console.log('allDay: ', eventData['allDay']);
	console.log('----------fill event source ------------');
	if(eventData['id'] != undefined){
		$("#id").val(eventData['id']);
		console.log('id');
	}
	if(eventData['title'] != undefined){
		$("#title").val(eventData['title']);
		console.log('title');
	}
	if(eventData['start'] != undefined){
		$("#date").val(dateToString(eventData['start']));
		console.log('date');
		
		if(eventData['start'].getHours() == 0 && eventData['start'].getMinutes() == 0){
			$("#startH").val(new Date().getHours());
			console.log('startH');
			$("#startM").val(new Date().getMinutes());
			console.log('startM');
			$("#endH").val(new Date().getHours());
			console.log('endH');
			$("#endM").val(new Date().getMinutes());
			console.log('endM');
			
		}else{
			
			$("#startH").val(intOn2Digit(eventData['start'].getHours()));
			console.log('startH');
			$("#startM").val(intOn2Digit(eventData['start'].getMinutes()));
			console.log('startM');
			if(eventData['end'] != undefined){
				$("#endH").val(intOn2Digit(eventData['end'].getHours()));
				console.log('endH');
				$("#endM").val(intOn2Digit(eventData['end'].getMinutes()));
				console.log('endM');
			}
			
		}
	}
	
	if(eventData['repeatMode'] != undefined){
		$("#repeatMode").attr('value',eventData['repeatMode']);
		console.log('repeatMode');
	}
	if(eventData['repeatEnd'] != undefined){
		if(eventData['repeatEnd'] != undefined){
			$("#repeatEnd").val(eventData['repeatEnd']);
			console.log('repeatEnd');
		}
	}
	if(eventData['description'] != undefined){
		$("#description").val(eventData['description']);
		console.log('description');
	}
	if(eventData['allDay'] != undefined){
		if(eventData['allDay']){
			//$('#allDay').attr('checked', 'checked');
			//$('#allDay').prop("checked", true);
			console.log('allDay');
		}
	}
	
	eventData = null;
	
	
	
	
	
	
	
	
	
	
	/*if(eventData['allDay']){
		//$('#allDay').attr('checked', 'checked');
		//$('#allDay').prop("checked", true);
		console.log('allDay');
	}
	*/
}