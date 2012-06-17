package calendar.tools.test.entity;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.entity.EventDate;
import calendar.core.ejb.entity.NormalEvent;
import calendar.core.ejb.session.EventHandler;
import calendar.core.ejb.session.PersistException;
import calendar.management.init.Config;
import calendar.tools.utils.DateHelper;

public class EventHandlerTest {
	
	EventHandler eventHandler = null;

	@Before
	public void setUp() {
		try {
			Context context = new InitialContext();
			eventHandler = (EventHandler) context.lookup("calendarEAR/EventBean/remote");

			
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
		}
	}
	
	/*@Test
	public void testDeleteEvent() throws PersistException {
		System.out.println("testDeleteEvent");
		Event event = new Event(6);
		eventHandler.delete(event);
	}*/
	@Test
	public void testUpdateEvent() throws ParseException, PersistException {
		System.out.println("testUpdateEvent");
		Event event = new NormalEvent(35, "event adsfadsf",
				"description adsfadsf");
		EventDate eventDate = new EventDate(DateHelper.StringToDate(
				"2012-06-16 08:00", Config.DATE_FORMAT_LONG),
				DateHelper.StringToDate("2012-06-16 10:00",
						Config.DATE_FORMAT_LONG));
		eventDate.setEvent(event);
		event.addEventDate(eventDate);

		eventHandler.update(event);
		
		HashMap<String, Object> filter = new HashMap<String, Object>();
		

		
		List<Event> events = eventHandler.read(filter);
		for (Event rEvent : events) {
			System.out.println(rEvent);
		}
	}
}
