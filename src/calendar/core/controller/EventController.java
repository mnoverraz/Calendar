package calendar.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.exception.TimeSlotException;
import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class EventController extends Controller {
	
	public void addEvent() throws TimeSlotException {
		TimeSlotException timeSlotException = new TimeSlotException();
		timeSlotException.eventDates = new ArrayList<EventDate>();
		throw timeSlotException;
	}
	
	public ArrayList<Event> getDummyEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
		Calendar calendar = Calendar.getInstance();
		
		int month = Calendar.MONTH;
		int year = Calendar.YEAR;
		
		System.out.println(year);
		
		Date date1 = null;
		Date date2 = null;
		
		try {
			date1 = DateHelper.StringToDate("2012-05-15 08:00", Config.DATE_FORMAT_LONG);
			date2 = DateHelper.StringToDate("2012-05-15 10:00", Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventDates.add(new EventDate(calendar.getTime(), date2));
		eventDates.add(new EventDate(DateHelper.getToday(), DateHelper.getToday()));
		
		Event event1 = new Event(1, eventDates, "reccurent 1", "description");
		
		events.add(event1);
		
		return events;
	}
	public ArrayList<Event> getEvents() {
		
		return null;
	}
}
