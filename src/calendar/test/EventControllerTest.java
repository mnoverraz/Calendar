package calendar.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import calendar.core.application.BootStrap;
import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.core.model.Event;
import calendar.core.model.EventDate;
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
	public void testWebEventController_shouldReturnEventsAsJSON() {
		BootStrap.init();
		WebEventController webEventController = new WebEventController(new EventController());
		Message message = new Message();
		message = (Message) webEventController.read(new HashMap<String, String>());

		String output = message.toJSON(true);
		
		System.out.println(output);
	}	
	
	@Test(expected=TimeSlotException.class)
	public void testAvailability_shouldTrowCoreException() throws CoreException {
		BootStrap.init();
		EventController eventController = new EventController();
		Event eventToTest = null;
		
		ArrayList<EventDate>eventDates = new ArrayList<EventDate>();
		try {
			eventDates.add(new EventDate(DateHelper.StringToDate("2012-05-05 22:50", Config.DATE_FORMAT_LONG), DateHelper.StringToDate("2012-05-05 23:30", Config.DATE_FORMAT_LONG)));
			eventDates.add(new EventDate(DateHelper.StringToDate("2012-05-05 21:40", Config.DATE_FORMAT_LONG), DateHelper.StringToDate("2012-05-05 22:10", Config.DATE_FORMAT_LONG)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventToTest = new Event(4, eventDates, "event 3", "description 3");
		

		eventController.create(eventToTest);
	}
}
