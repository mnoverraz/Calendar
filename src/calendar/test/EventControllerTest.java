package calendar.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import calendar.core.application.BootStrap;
import calendar.core.controller.EventController;
import calendar.web.controller.WebEventController;
import calendar.web.renderer.Message;
import calendar.web.renderer.Renderer;

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
	public void testWebEventController() {
		BootStrap.init();
		WebEventController webEventController = new WebEventController(new EventController());
		Message message = new Message();
		message = (Message) webEventController.read(new HashMap<String, String>());

		String output = Renderer.toJSON(message);
		
		System.out.println(output);
	}	
}
