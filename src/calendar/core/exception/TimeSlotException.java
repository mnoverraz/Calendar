package calendar.core.exception;

import java.util.ArrayList;

import calendar.core.model.EventDate;

public class TimeSlotException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
	

}
