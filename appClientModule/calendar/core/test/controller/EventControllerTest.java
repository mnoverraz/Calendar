package calendar.core.test.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import calendar.core.application.BootStrap;
import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;

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

	/*@Test(expected=TimeSlotException.class)
	public void testAvailability_throwsTimeSlotException() throws CoreException, NamingException {
		Event eventToTest = new Event(4, "event 3", "description 3", "m");
		

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
	public void testGetEvents() throws CoreException {
		HashMap<String, Object> filter = new HashMap<String, Object>();

		ArrayList<Event> events = eventController.read(filter);
		

		for (Event event : events) {
			System.out.println(event);
		}

	}
}
