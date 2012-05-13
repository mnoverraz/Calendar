package calendar.core.controller;

import java.util.ArrayList;
import java.util.Date;

import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class EventController {
	public ArrayList<Event> getEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
		
		eventDates.add(new EventDate(new Date(), new Date()));
		eventDates.add(new EventDate(new Date(), new Date()));
		
		Event event1 = new Event(1, eventDates, "title", "description");
		
		events.add(event1);
		events.add(event1);
		
		return events;
	}
	
}
