package calendar.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import calendar.core.application.BootStrap;
import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.web.controller.WebEventController;
import calendar.web.renderer.Message;

public class EventControllerTest {

	/*@Test
	public void testGetEvents() {
		EventController eventController = new EventController();
		ArrayList<Event> events = eventController.getEvents();
		

		for (Event event : events) {
			System.out.println(event);
		}

	}*/

	
	@Test
	public void testWebEventController_shouldReturnEventsAsJSON() throws NamingException {
		BootStrap.init();
		WebEventController webEventController = new WebEventController(new EventController(new InitialContext()));
		Message message = new Message();
		message = (Message) webEventController.read(new HashMap<String, String>());

		String output = message.toJSON(true);
		
		System.out.println(output);
	}	
	
	@Test(expected=TimeSlotException.class)
	public void testAvailability_throwsTimeSlotException() throws CoreException, NamingException {
		BootStrap.init();
		EventController eventController = new EventController(new InitialContext());
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
	

	public void testWebEventController_throwsNoException() throws NamingException {
		BootStrap.init();
		EventController eventController = new EventController(new InitialContext());
		WebEventController controller = new WebEventController(eventController);
		Event eventToTest  = new Event(4, "event 3", "description 3", "m");
		Message message = null;
		
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
	}
}
