import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.entity.Room;
import calendar.core.entity.RoomCategory;
import calendar.core.session.EventHandlerRemote;
import calendar.core.session.PersistException;
import calendar.core.session.RoomCategoryHandlerRemote;
import calendar.core.session.RoomHandlerRemote;

public class Main {
	public static void main(String[] args) throws NamingException,
			ParseException, PersistException {
		addEvents();
		addRoomsAndCategories();
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

		event = new Event(0, "エベント　２", "詳細がありません。", "d", DateHelper.StringToDate("2012-07-16"));
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
		
		Context context;
		RoomHandlerRemote roomHandler;
		RoomCategoryHandlerRemote roomCategoryHandler;
		context = new InitialContext();
		roomHandler = (RoomHandlerRemote) context
				.lookup("calendarEAR/RoomBean/remote");
		roomCategoryHandler = (RoomCategoryHandlerRemote) context
				.lookup("calendarEAR/RoomCategoryBean/remote");
		/*
		 * Dummy data for test, real data access should be here
		 */
//'219', 'Salle de conférence', '', '12 pl. assises + 9 chaises, rétro-projecteur, tableau blanc, vidéoconférence', 0, 1),
		Room room1 = new Room(0, "313", "Microscope à fluorescence", "Please indicate if you use fluorescence or not (fluo oui, fluo non).");
		Room room2 = new Room(0, "219", "Salle de conférence", "12 pl. assises + 9 chaises, rétro-projecteur, tableau blanc, vidéoconférence");
		Room room3 = new Room(0, "006", "Animalerie", "");
		
//		roomHandler.create(room1);
//		roomHandler.create(room2);
//		roomHandler.create(room3);

		RoomCategory roomCategory1 = new RoomCategory(0, "Room");
		RoomCategory roomCategory2 = new RoomCategory(0, "Hall");

		roomCategory1.addRoom(room1);
		roomCategory1.addRoom(room2);
		roomCategory2.addRoom(room3);

		roomCategoryHandler.create(roomCategory1);
		roomCategoryHandler.create(roomCategory2);
		
	}
	
}