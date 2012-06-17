package calendar.tools.test.entity;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.session.EventHandler;
import calendar.core.ejb.session.PersistException;

public class EventHandlerTest {
	
	EventHandler eventHandler = null;

	@Before
	public void setUp() {
		try {
			Context context = new InitialContext();
			eventHandler = (EventHandler) context.lookup("calendarEAR/EventBean/remote");
			/*
			 * Inserts test data
			 */
			
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	@Test
	public void testReadWithStartEndFilter() throws PersistException {
		System.out.println("testReadWithStartEndFilter");
		HashMap<String, Object> filter = new HashMap<String, Object>();

		long startS = 1338156000L;
		long endS = 1341784800L;
		
		Date start = new Date(startS * 1000);
		filter.put("start", start);
		
		Date end = new Date(endS * 1000);
		filter.put("end", end);

		List<Event> events = eventHandler.read(filter);
		for (Event event : events) {
			System.out.println(event);
			/*for (EventDate eventDate : event.getEventDates()) {
				System.out.println("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(
						eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(
						eventDate.getEnd(), Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());
				eventMap.put("description", event.getDescription());
				eventMap.put("repeatMode", event.getMode());
	
				message.addElementToBody(eventMap);
			}*/
		}
	}
	
	@Test
	public void testDeleteEvent() throws PersistException {
		System.out.println("testReadWithStartEndFilter");
		Event event = new Event(6);
		eventHandler.delete(event);
	}
}
