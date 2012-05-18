/**
 * @author:     Stefan Meier
 * @version:    20110711
 * 
 * This script is a listener for all eventdialog events
 */

function repeatSelector() {
    if ($("#repeat :selected").val() == 'n') {
        $("#repeat_date").hide();
        $("#repeat_end").removeClass('required');
    }
    else {
        $("#repeat_date").show();
        $("#repeat_end").addClass('required');
    }
}

$(document).ready(function() {
    $('#name').focus();

    if ($("#edate").val() == '') {

        $("#edate").val(caldate);
    }
    if ($("#repeat_end").val() == '') {
        $("#repeat_end").val(caldate);
    }
    $("#edate").change(function() {

        eventDate = $("#edate").val();
        repeatEnd = $("#repeat_end").val();
        
        if (eventDate > repeatEnd) {
        	$("#repeat_end").val(eventDate);
        }
    });

    $("#start_hour").change(function() {
        startHour = $("#start_hour").val();
		startHour = parseInt(startHour);
		endHour = 23;

        if (startHour < 23) {
            endHour =  startHour + 1;
            if (endHour < 10) {
                endHour = '0' + endHour;
            }
        }
		$("#end_hour").val(endHour);
    });

    $("#repeat_date").hide();
    // Datepicker
    $(".datepicker").datepicker({
        //dayNamesMin: [resourceBundle["day-1-short"], resourceBundle["day-2-short"], resourceBundle["day-3-short"], resourceBundle["day-4-short"], resourceBundle["day-5-short"], resourceBundle["day-6-short"], resourceBundle["day-7-short"]],
        //monthNames: [resourceBundle["month-1-full"],resourceBundle["month-2-full"],resourceBundle["month-3-full"],resourceBundle["month-4-full"],resourceBundle["month-5-full"],resourceBundle["month-6-full"],resourceBundle["month-7-full"],resourceBundle["month-8-full"],resourceBundle["month-9-full"],resourceBundle["month-10-full"],resourceBundle["month-11-full"],resourceBundle["month-12-full"]],
        firstDay: 1,
        maxDate: (new Date().getFullYear() + maxYearOffset) + '-12-31',
        minDate: new Date(),
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
		changeYear: true
    });

    wholeDay();

    $('#whole_day').change(function() {
        wholeDay();
    });
    repeatSelector();
    $('#repeat').change(function() {
        repeatSelector();
    });
});
