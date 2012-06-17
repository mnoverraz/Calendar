package calendar.tools.test.controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import calendar.management.controller.EventController;
import calendar.web.controller.WebEventController;
import calendar.web.renderer.Message;

public class WebEventControllerTest {

	private WebEventController eventController = null;

	@Before
	public void setUp() {
		try {
			eventController = new WebEventController(new EventController(
					new InitialContext(), "remote"));
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		System.out.println("create");

		String fId = "";
		String fDate = "2012-06-01";
		String fStartH = "04";
		String fStartM = "30";
		String fEndH = "15";
		String fEndM = "40";
		String fAllDay = "off";
		String fRepeatMode = "d";
		String fRepeatEnd = "2012-06-30";
		String fTitle = "repeatDaily 2";
		String fDescription = "description";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", fId);
		params.put("date", fDate);
		params.put("startH", fStartH);
		params.put("startM", fStartM);
		params.put("endH", fEndH);
		params.put("endM", fEndM);
		params.put("allDay", fAllDay);
		params.put("repeatMode", fRepeatMode);
		params.put("repeatEnd", fRepeatEnd);
		params.put("title", fTitle);
		params.put("description", fDescription);

		Message message = eventController.create(params);
		System.out.println(message.toJSON(true));
	}

	@Test
	public void testReadWithBlankFilter() {
		System.out.println("testReadWithBlankFilter");
		HashMap<String, String> filter = new HashMap<String, String>();

		Message message = eventController.read(filter);
		System.out.println(message.toJSON(true));
	}

	@Test
	public void testReadWithStartEndFilter() {
		System.out.println("testReadWithStartEndFilter");
		HashMap<String, String> filter = new HashMap<String, String>();

		String start = "1338156000";
		String end = "1341784800";

		filter.put("start", start);
		filter.put("end", end);

		Message message = eventController.read(filter);
		System.out.println(message.toJSON(true));
	}

}
