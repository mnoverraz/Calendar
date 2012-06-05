package calendar.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.NamingException;

import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.SystemException;
import calendar.core.exception.TimeSlotException;
import calendar.core.session.EventHandlerLocal;
import calendar.core.session.PersistException;


public class EventController extends Controller<Event> {	
	private EventHandlerLocal eventHandler;
	public EventController(Context context) throws NamingException {
		super(context);
		this.eventHandler = (EventHandlerLocal) context.lookup("calendarEAR/EventBean/local");
	}

	@Override
	public void create(Event event) throws CoreException {
		
		if (checkAvailability(event)) {
			try {
				eventHandler.create(event);
			} catch (PersistException e) {
				SystemException se = new SystemException();
				se.detailInformation = e;
			}
		}

		
	}

	@Override
	public ArrayList<Event> read(HashMap<String, Object> filter) throws CoreException {
		ArrayList<Event> events = new ArrayList<Event>();
		Date start = null;
		Date end = null;
		
		if (filter != null) {
			Iterator<Entry<String, Object>> it = filter.entrySet().iterator();			
			while (it.hasNext()) {
				Object key = it.next().getKey();
				Object value = filter.get(key);
				
				if ("start".equals(key))
					start = (Date) value;
				if ("end".equals(key))
					end = (Date) value;
			}
		}
		try {
			events = (ArrayList<Event>) eventHandler.read(null);
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}		
		return events;
	}

	@Override
	public void update(Event event) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Event event) throws CoreException {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized void doAction(String action, Event event) {
		if ("delete".equals(action)) {
			
		}
		else if ("create".equals(	action)) {
			
		}
		else if ("update".equals(action)) {
			
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
