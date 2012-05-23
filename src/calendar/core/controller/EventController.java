package calendar.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class EventController extends Controller<Event> {	
	@Override
	public synchronized void create(Event event) throws CoreException {
		
		boolean available = checkAvailability(event);
		
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
		
		/*
		 * Dummy data for test, real data access should be here
		 */
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
		Calendar calendar = Calendar.getInstance();

		Date date2 = null;
		
		try {
			date2 = DateHelper.StringToDate("2012-05-15 10:00", Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventDates.add(new EventDate(calendar.getTime(), date2));
		eventDates.add(new EventDate(DateHelper.getToday(), DateHelper.getToday()));
		
		Event event1 = new Event(1, eventDates, "reccurent 1", "description");
		
		events.add(event1);
		/*
		 * End dummy
		 */
		
		return events;
	}

	@Override
	public synchronized void update(Event event) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void delete(Event event) throws CoreException {
		// TODO Auto-generated method stub
		
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
						unavailableEvents.add(existingEventDate);
					
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
