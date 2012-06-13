package calendar.core.test.entity;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.session.EventBean;
import calendar.core.session.EventHandler;
import calendar.core.session.PersistException;
import calendar.web.controller.WebEventController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;

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

		String start = "1338156000";
		String end = "1341784800";

		filter.put("start", start);
		filter.put("end", end);

		List<Event> events = eventHandler.read(filter);
		for (Event event : events) {
			System.out.println(event);
			/*for (EventDate eventDate : event.getEventDates()) {
				System.out.println("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(
						eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(
						eventDate.getEnd(), Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());
				eventMap.put("description", event.getDescription());
				eventMap.put("repeatMode", event.getMode());
	
				message.addElementToBody(eventMap);
			}*/
		}

	}
}
