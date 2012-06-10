package calendar.core.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.entity.Room;
import calendar.core.entity.RoomCategory;
import calendar.core.session.EventHandlerRemote;
import calendar.core.session.PersistException;
import calendar.core.session.RoomCategoryHandlerRemote;
import calendar.core.session.RoomHandlerRemote;

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
	
	@Test
	public void testReceptionAllRooms() {
		Context context;
		List<Room> rooms;
		RoomHandlerRemote roomHandler;
		try {
			context = new InitialContext();
			roomHandler = (RoomHandlerRemote) context.lookup("calendarEAR/RoomBean/remote");
			rooms = roomHandler.read(null);
			for (Room room : rooms) {
				System.out.println(room.getId());
				System.out.println(room.getLocal());
				System.out.println(room.getName());
				System.out.println(room.getDescription());
				System.out.println(room.getRoomCategory().getName());
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
	
	@Test
	public void testReceptionAllRoomCategories() {
		Context context;
		List<RoomCategory> roomCategories;
		RoomCategoryHandlerRemote roomCategoryHandler;
		try {
			context = new InitialContext();
			roomCategoryHandler = (RoomCategoryHandlerRemote) context.lookup("calendarEAR/RoomCategoryBean/remote");
			roomCategories = roomCategoryHandler.read(null);
			for (RoomCategory roomCategory : roomCategories) {
				System.out.println(roomCategory.getId());
				System.out.println(roomCategory.getName());
				for (Room room : roomCategory.getRooms()) {
					System.out.println(room.getName());
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
