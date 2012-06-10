import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.session.EventHandlerRemote;
import calendar.core.session.PersistException;

public class Main {
	public static void main(String[] args) throws NamingException,
			ParseException, PersistException {
		addEvents();
	}
	
	public static void addEvents() throws NamingException, ParseException, PersistException {
		Context context;
		EventHandlerRemote eventHandler;
		context = new InitialContext();
		eventHandler = (EventHandlerRemote) context
				.lookup("calendarEAR/EventBean/remote");
		/*
		 * Dummy data for test, real data access should be here
		 */


		Event event = new Event(0, "event 1",
				"description 1", "m");
		EventDate eventDate = new EventDate(DateHelper.StringToDate(
				"2012-06-15 08:00", Config.DATE_FORMAT_LONG),
				DateHelper.StringToDate("2012-06-15 10:00",
						Config.DATE_FORMAT_LONG));
		eventDate.setEvent(event);
		event.addEventDate(eventDate);

		eventHandler.create(event);

		event = new Event(0, "event 2", "description 2", "");
		eventDate = new EventDate(DateHelper.StringToDate("2012-06-16 11:00",
				Config.DATE_FORMAT_LONG), DateHelper.StringToDate(
				"2012-06-16 13:00", Config.DATE_FORMAT_LONG));
		eventDate.setEvent(event);
		event.addEventDate(eventDate);

		eventHandler.create(event);

		event = new Event(0, "event 3", "description 3", "");
		eventDate = new EventDate(DateHelper.StringToDate("2012-06-05 22:00",
				Config.DATE_FORMAT_LONG), DateHelper.StringToDate(
				"2012-06-05 23:00", Config.DATE_FORMAT_LONG));
		eventDate.setEvent(event);
		event.addEventDate(eventDate);

		eventHandler.create(event);

		event = new Event(0, "event 4", "description 4", "");
		eventDate = new EventDate(DateHelper.StringToDate("2012-06-07 22:30",
				Config.DATE_FORMAT_LONG), DateHelper.StringToDate(
				"2012-06-07 23:45", Config.DATE_FORMAT_LONG));
		eventDate.setEvent(event);
		event.addEventDate(eventDate);

		eventHandler.create(event);

	}
}