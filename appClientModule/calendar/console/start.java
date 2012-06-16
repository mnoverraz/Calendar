package calendar.console;
import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.entity.EventDate;
import calendar.core.ejb.entity.Room;
import calendar.core.ejb.entity.RoomCategory;
import calendar.core.ejb.session.EventHandlerRemote;
import calendar.core.ejb.session.PersistException;
import calendar.core.ejb.session.RoomCategoryHandlerRemote;
import calendar.management.init.Config;
import calendar.tools.utils.DateHelper;

public class start {
	private static Context context;
	public static void main(String[] args) throws NamingException,
			ParseException, PersistException {
		addEvents();
		addRoomsAndCategories();
	}
	
	public static void addEvents() throws NamingException, ParseException, PersistException {
		EventHandlerRemote eventHandler;
		context = new InitialContext();
		eventHandler = (EventHandlerRemote) context
				.lookup("calendarEAR/EventBean/remote");

		Event event = new Event(0, "event 1",
				"description 1", "m");
		EventDate eventDate = new EventDate(DateHelper.StringToDate(
				"2012-06-15 08:00", Config.DATE_FORMAT_LONG),
				DateHelper.StringToDate("2012-06-15 10:00",
						Config.DATE_FORMAT_LONG));
		eventDate.setEvent(event);
		event.addEventDate(eventDate);

		eventHandler.create(event);

		event = new Event(0, "米婚の大切なやつ", "皆バーツブン", "d", DateHelper.StringToDate("2012-07-16"));
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
	
	public static void addRoomsAndCategories() throws NamingException, ParseException, PersistException {
		RoomCategoryHandlerRemote roomCategoryHandler;
		context = new InitialContext();
		roomCategoryHandler = (RoomCategoryHandlerRemote) context
				.lookup("calendarEAR/RoomCategoryBean/remote");

		Room room1 = new Room(0, "313", "Microscope à fluorescence", "Please indicate if you use fluorescence or not (fluo oui, fluo non).");
		Room room2 = new Room(0, "219", "Salle de conférence", "12 pl. assises + 9 chaises, rétro-projecteur, tableau blanc, vidéoconférence");
		Room room3 = new Room(0, "006", "Animalerie", "Only permitted persons can access the animal facility.");

		RoomCategory roomCategory1 = new RoomCategory(0, "Appareil scientifique");
		RoomCategory roomCategory2 = new RoomCategory(0, "Salles");
		RoomCategory roomCategory3 = new RoomCategory(0, "Animalerie");

		roomCategory1.addRoom(room1);
		roomCategory2.addRoom(room2);
		roomCategory3.addRoom(room3);

		roomCategoryHandler.create(roomCategory1);
		roomCategoryHandler.create(roomCategory2);	
		roomCategoryHandler.create(roomCategory3);	
	}
}