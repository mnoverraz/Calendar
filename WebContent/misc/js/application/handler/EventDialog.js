/**
 * @author:     Stefan Meier

 * @version:    20110711
 * 
 * This script is a listener for all eventdialog events
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
        $("#date").val(caldate);
    }

    if ($("#repeatEnd").val() == '') {
        $("#repeatEnd").val(caldate);
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
    
    //Let this line commented to show for the first time the repeat date.
    //$("#repeatEnd").hide();
    
    // Datepicker
    $(".datepicker").datepicker({
        //dayNamesMin: [resourceBundle["day-1-short"], resourceBundle["day-2-short"], resourceBundle["day-3-short"], resourceBundle["day-4-short"], resourceBundle["day-5-short"], resourceBundle["day-6-short"], resourceBundle["day-7-short"]],
        //monthNames: [resourceBundle["month-1-full"],resourceBundle["month-2-full"],resourceBundle["month-3-full"],resourceBundle["month-4-full"],resourceBundle["month-5-full"],resourceBundle["month-6-full"],resourceBundle["month-7-full"],resourceBundle["month-8-full"],resourceBundle["month-9-full"],resourceBundle["month-10-full"],resourceBundle["month-11-full"],resourceBundle["month-12-full"]],
        firstDay: 1,
        //maxDate: (new Date().getFullYear() + maxYearOffset) + '-12-31',
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
    /*if (action == 'delete') {
        $("#action").val("delete");
    }
    $('.ui-dialog').block({ 
        message: '<img src="misc/img/loading.gif" />', 
        css: { border: 'none' } 
    }); */
    dataString = $('#eventform').serialize();
    send(url, dataString, method);
    

}

function fillEvent(){
	/*alert(
			'id=' + eventData['id'] +
			'| title=' + eventData['title'] +
			'| date=' + eventData['start'].getFullYear() +
			'| allDay=' + eventData['allDay']
	);*/
	console.log('------------------');
	console.log('----FillEvent-----');
	console.log('	id: ' + eventData['id']);
	console.log('	title: ' + eventData['title']);
	console.log('	start: ' + eventData['start']);
	console.log('	end: ' + eventData['end']);
	console.log('	repeatMode: ' + eventData['repeatMode']);
	console.log('	repeatEnd: ' + eventData['repeatEnd']);
	console.log('	description: ' + eventData['description']);
	console.log('------------------');
	
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
	//$("#repeatEnd").val(dateToString(eventData['repeatEnd']));
	$("#description").val(eventData['description']);
	
	if(eventData['allDay']){
		$('#allDay').attr('checked', true);
	}
	//$("#repeatEnd").val(eventData['end'].getFullYear() + '-' + eventData['end'].getMonth() + '-' + eventData['end'].getDate());
	eventData = null;
	
}