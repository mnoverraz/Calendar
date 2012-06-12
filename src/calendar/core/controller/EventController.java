package calendar.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.NamingException;

import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.SystemException;
import calendar.core.exception.TimeSlotException;
import calendar.core.session.EventHandler;
import calendar.core.session.PersistException;


public class EventController extends Controller<Event> {	
	private EventHandler eventHandler;
	
	public EventController(Context context) throws NamingException {
		super(context);
		initialize("local");
	}
	
	public EventController(Context context, String source) throws NamingException {
		super(context);
		initialize("remote");
	}
	
	private void initialize(String source) throws NamingException {
		this.eventHandler = (EventHandler) context.lookup("calendarEAR/EventBean/" + source);
	}

	@Override
	public void create(Event event) throws CoreException {
		doAction("create", event);
	}

	@Override
	public ArrayList<Event> read(HashMap<String, Object> filter) throws CoreException {
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			events = (ArrayList<Event>) eventHandler.read(filter);
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}		
		return events;
	}

	@Override
	public void update(Event event) throws CoreException {
		doAction("update", event);
	}

	@Override
	public void delete(Event event) throws CoreException {
		doAction("delete", event);
	}
	
	private synchronized void doAction(String action, Event event) throws TimeSlotException, CoreException {
		try {
		if ("delete".equals(action))
			eventHandler.delete(event);
		else
			if (checkAvailability(event)) {
				if ("create".equals(action))
					eventHandler.create(event);
				else if ("update".equals(action))
					eventHandler.update(event);
			}
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
	}
	
	private boolean checkAvailability(Event event) throws CoreException, TimeSlotException {
		boolean available = true;
		
		ArrayList<EventDate> unavailableEvents = new ArrayList<EventDate>();
		
		HashMap<String, Object> filter = null;
		
		for (EventDate eventDate : event.getEventDates()) {
			filter = new HashMap<String, Object>();
			
			Date newStart = eventDate.getStart();
			Date newEnd = eventDate.getEnd();
			
			filter.put("start", newStart);
			filter.put("end", newEnd);
			
			ArrayList<Event> existingEvents = (ArrayList<Event>)this.read(filter);
			
			for (Event existingEvent : existingEvents) {
				for (EventDate existingEventDate : existingEvent.getEventDates()) {
					Date existingStart = existingEventDate.getStart();
					Date existingEnd = existingEventDate.getEnd();
					
					/*
					 * If the current event has the same id as the retrieved event or
					 * if the events end takes place before or at the same time as the existing event or
					 * if the events begin takes place after or at the same time as the existing events end
					 * then the event can be inserted
					 */
					available = event.getId() == existingEvent.getId() || (newEnd.before(existingStart) 
							|| newEnd.equals(existingStart)) || (newStart.after(existingEnd) 
							|| newStart.equals(existingEnd));
					
					if (!available)
						unavailableEvents.add(eventDate);
				}	
			}	
		}
		
		if (unavailableEvents.size() > 0) {
			TimeSlotException timeSlotException = new TimeSlotException();
			timeSlotException.detailInformation = unavailableEvents;
			throw timeSlotException;
		}
		
		return available;
		
	}
}
