package calendar.tools.test.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.entity.EventDate;
import calendar.core.ejb.entity.NormalEvent;
import calendar.core.exception.CoreException;
import calendar.management.controller.EventController;
import calendar.management.exception.TimeSlotException;
import calendar.management.init.Config;
import calendar.tools.utils.DateHelper;

public class EventControllerTest {
	
	private EventController eventController = null;

	@Before
	public void setUp() throws Exception {
		try {
			eventController = new EventController(
					new InitialContext(), "remote");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test(expected=TimeSlotException.class)
	public void testAvailability_throwsTimeSlotException() throws CoreException, NamingException {
		Event eventToTest = new NormalEvent(4, "event 3", "description 3");
		

		try {
			eventToTest.addEventDate(new EventDate(DateHelper.StringToDate("2012-06-05 22:50", Config.DATE_FORMAT_LONG), DateHelper.StringToDate("2012-06-05 23:30", Config.DATE_FORMAT_LONG)));
			eventToTest.addEventDate(new EventDate(DateHelper.StringToDate("2012-06-05 21:40", Config.DATE_FORMAT_LONG), DateHelper.StringToDate("2012-06-05 22:10", Config.DATE_FORMAT_LONG)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		eventController.create(eventToTest);
	}
	

/*	public void testWebEventController_throwsNoException() throws NamingException {
		Event eventToTest  = new Event(4, "event 3", "description 3", "m");
		
		try {
			eventToTest.addEventDate(new EventDate(DateHelper.StringToDate("2012-05-05 22:50", Config.DATE_FORMAT_LONG), DateHelper.StringToDate("2012-05-05 23:30", Config.DATE_FORMAT_LONG)));
			eventToTest.addEventDate(new EventDate(DateHelper.StringToDate("2012-05-05 21:40", Config.DATE_FORMAT_LONG), DateHelper.StringToDate("2012-05-05 22:10", Config.DATE_FORMAT_LONG)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			eventController.create(eventToTest);
		} catch (CoreException e) {
			
		}
	}*/

	@Test
	public void testReadAll() throws CoreException {
		System.out.println("testReadAll");
		HashMap<String, Object> filter = new HashMap<String, Object>();

		ArrayList<Event> events = eventController.read(filter);
		

		for (Event event : events) {
			System.out.println(event);
		}

	}
	
	@Test
	public void testReadWithStartEndFilter() throws CoreException {
		System.out.println("testReadWithStartEndFilter");
		HashMap<String, Object> filter = new HashMap<String, Object>();
		
		long startS = 1338156000L;
		long endS = 1341784800L;
		
		Date start = new Date(startS * 1000);
		filter.put("start", start);
		
		Date end = new Date(endS * 1000);
		filter.put("end", end);

		ArrayList<Event> events = eventController.read(filter);

		for (Event event : events) {
			System.out.println(event);
		}

	}
}
