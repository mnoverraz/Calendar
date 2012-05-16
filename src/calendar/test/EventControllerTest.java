package calendar.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import calendar.core.controller.EventController;
import calendar.core.model.Event;
import calendar.web.controller.WebEventController;
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
		WebEventController webEventController = new WebEventController();

		ArrayList<HashMap<String, Object>> ret = webEventController.read(new HashMap<String, String>());
		System.out.println(ret);
		String output = Renderer.toJSON(ret);
		
		System.out.println(output);
	}	
}
