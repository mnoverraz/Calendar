package calendar.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import calendar.core.entity.Event;
import calendar.core.session.*;


public class RemoteEvent {

	@Test
	public void receivesEvensAsList() {
		Context context;
		List<Event> events;
		EventHandlerLocal eventHandler;
		try {
			context = new InitialContext();
			eventHandler = (EventHandlerLocal) context.lookup("calendarEAR/EventHandler/local");
			System.out.println("hello");
			events = eventHandler.get();
			for (Event event : events) {
				System.out.println(event.getTitle());
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
