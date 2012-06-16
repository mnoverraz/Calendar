package calendar.tools.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.session.EventHandler;
import calendar.core.ejb.session.PersistException;


public class RemoteEvent {

	@Test
	public void receivesEvensAsList() {
		Context context;
		List<Event> events;
		EventHandler eventHandler;
		try {
			context = new InitialContext();
			eventHandler = (EventHandler) context.lookup("calendarEAR/EventBean/remote");
			events = eventHandler.read(null);
			for (Event event : events) {
				System.out.println(event.getTitle());
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
