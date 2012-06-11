/**
 * @author Stefan Meier
 * @version 20110715
 * 
 * Contains date manipulation functions
 */

/**
 * 
 * @param dateString
 * @returns date
 */
function stringToDate(dateString) {
    dateParts = dateString.match(/(\d+)/g);
    realDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]);  
                                                                // months are 0-based!
    return realDate;
}

function intOn2Digit(number){
	
	if(number <= 9){
		console.log('0' + String(number));
		return '0' + String(number);
	}else{
		console.log(number);
		return number;
	}
}

function dateToString(date) {
        dateString = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
        return dateString;

}

function getToday() {
        return new Date();
}


function getNextMonth(date) {
        if (date.getMonth() == 11) {
            date = new Date(date.getFullYear() + 1, 0, 1);
        } else {
            date = new Date(date.getFullYear(), date.getMonth() + 1, 1);
        }
        return date;
}

function getPreviousMonth(date) {
        if (date.getMonth() == 0) {
            date = new Date(date.getFullYear() -1, 11, 1);
        } else {
            date = new Date(date.getFullYear(), date.getMonth() - 1, 1);
        }
        return date;    
}