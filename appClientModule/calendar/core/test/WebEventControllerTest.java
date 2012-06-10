package calendar.core.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import calendar.core.controller.EventController;
import calendar.core.entity.Event;
import calendar.web.controller.WebEventController;
import calendar.web.renderer.Message;

public class WebEventControllerTest {

	@Test
	public void testCreate() {

			WebEventController eventController = null;
			try {
				eventController = new WebEventController(new EventController(new InitialContext(), "remote"));
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Event event = null;
			/**
			 * title=sadf&id=&date=2012-06-27&allDay=on&startH=00&startM=00&endH=00&endM=00&repeatMode=n&repeatEnd=2012-06-27&description=
			 */
			String fId = "";
			String fDate = "2012-06-22";
			String fStartH = "04";
			String fStartM = "30";
			String fEndH = "15";
			String fEndM = "40";
			String fAllDay = "off";
			String fRepeatMode = "d";
			String fRepeatEnd = "2012-06-30";
			String fTitle = "repeatDaily 2";
			String fDescription = "";

			HashMap<String, String> params = new HashMap<String, String>();
			params.put("id", fId);
			params.put("date", fDate);
			params.put("startH", fStartH);
			params.put("startM",fStartM);
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

}
