package calendar.core.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.session.EventHandlerRemote;
import calendar.core.session.PersistException;

public class EntityTest {

	@Test
	public void testReceptionAllEvents() {
		Context context;
		List<Event> events;
		EventHandlerRemote eventHandler;
		try {
			context = new InitialContext();
			eventHandler = (EventHandlerRemote) context.lookup("calendarEAR/EventBean/remote");
			events = eventHandler.read(null);
			for (Event event : events) {
				System.out.println(event.getId());
				System.out.println(event.getTitle());
				System.out.println(event.getMode());
				System.out.println(event.getDescription());
				for (EventDate dates : event.getEventDates()) {
					System.out.println(dates.getStart());
					System.out.println(dates.getEnd());
				}
				System.out.println("------");
				
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
