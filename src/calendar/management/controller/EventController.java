package calendar.management.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.NamingException;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.entity.EventDate;
import calendar.core.ejb.session.EventHandler;
import calendar.core.ejb.session.PersistException;
import calendar.core.exception.CoreException;
import calendar.management.exception.SystemException;
import calendar.management.exception.TimeSlotException;

/**
 * Specific controller to handle Event data. 
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class EventController extends Controller<Event> {	
	private EventHandler eventHandler;
	
	/**
	 * This constructor is called if a local context is passed as parameter
	 * 
	 * @param context
	 * @throws NamingException
	 */
	public EventController(Context context) throws NamingException {
		super(context);
		initialize("local");
	}
	
	/**
	 * This constructor is only called if a specific context source needs to 
	 * be specified (for example to test this class in a JUnit test)
	 * 
	 * @param context
	 * @param source
	 * @throws NamingException
	 */
	public EventController(Context context, String source) throws NamingException {
		super(context);
		initialize(source);
	}
	
	/**
	 * Initializes the EJB handler on which the controller applies
	 * 
	 * @param source
	 * @throws NamingException
	 */
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
	
	/**
	 * Executes synchronized action in order to prevent inconsistent data
	 * @param action
	 * @param event
	 * @throws TimeSlotException
	 * @throws CoreException
	 */
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
	
	/**
	 * Checks if the time slot for the event in the parameter is available or not
	 * 
	 * @param event
	 * @return boolean
	 * @throws CoreException
	 * @throws TimeSlotException
	 */
	private boolean checkAvailability(Event event) throws CoreException, TimeSlotException {
		boolean available = true;
		/*
		 * Holds all unavailable time slots
		 */
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
		
		/*
		 * If at least on unavailable slot has been found, a TimeSlotException is thrown 
		 */
		if (unavailableEvents.size() > 0) {
			TimeSlotException timeSlotException = new TimeSlotException();
			timeSlotException.detailInformation = unavailableEvents;
			throw timeSlotException;
		}
		
		return available;
		
	}
}
