package calendar.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import calendar.controller.EventController;
import calendar.model.Event;

public class EventControllerTest {

	@Test
	public void testGetEvents() {
		EventController eventController = new EventController();
		ArrayList<Event> events = eventController.getEvents();
		

		for (Event event : events) {
			System.out.println(event);
		}

	}

}
