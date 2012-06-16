package calendar.management.exception;

import calendar.core.exception.CoreException;
/**
 * This exception is thrown if one or more time slots are unavailable
 * detailInformation will be stored as ArrayList<EventDates>
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class TimeSlotException extends CoreException {
	private static final long serialVersionUID = 1L;
}
