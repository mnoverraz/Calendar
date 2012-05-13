package calendar.controller;

import java.util.ArrayList;
import java.util.Date;

import calendar.model.Event;
import calendar.model.EventDate;


public class EventController {
	public ArrayList<Event> getEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
		
		eventDates.add(new EventDate(new Date(), new Date()));
		eventDates.add(new EventDate(new Date(), new Date()));
		
		events.add(new Event(1, eventDates, "title", "description"));
		
		return events;
	}
	
}
