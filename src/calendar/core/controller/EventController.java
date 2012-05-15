package calendar.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class EventController {
	public ArrayList<Event> getEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
		
		Date date1 = null;
		Date date2 = null;
		
		try {
			date1 = DateHelper.StringToDate("2012-05-15 08:00", Config.DATE_FORMAT_LONG);
			date2 = DateHelper.StringToDate("2012-05-15 10:00", Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventDates.add(new EventDate(date1, date2));
		eventDates.add(new EventDate(DateHelper.getToday(), DateHelper.getToday()));
		
		Event event1 = new Event(1, eventDates, "reccurent 1", "description");
		
		events.add(event1);
		
		return events;
	}
	
}
