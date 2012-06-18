package calendar.management.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.NamingException;

import calendar.core.ejb.entity.Room;
import calendar.core.ejb.session.PersistException;
import calendar.core.ejb.session.RoomHandlerLocal;
import calendar.core.exception.CoreException;
import calendar.management.exception.SystemException;

/**
 * Specific controller to handle Room data.
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class RoomController extends Controller<Room> {

	private RoomHandlerLocal roomHandler;

	public RoomController(Context context) throws NamingException {
		super(context);
		this.roomHandler = (RoomHandlerLocal) context
				.lookup("calendarEAR/RoomBean/local");
	}

	@Override
	public void create(Room room) throws CoreException {
		doAction("create", room);
	}

	@Override
	public ArrayList<Room> read(HashMap<String, Object> filter)
			throws CoreException {
		ArrayList<Room> rooms = new ArrayList<Room>();
		try {
			rooms = (ArrayList<Room>) roomHandler.read(filter);
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
		return rooms;
	}

	@Override
	public void update(Room room) throws CoreException {
		doAction("update", room);
	}

	@Override
	public void delete(Room room) throws CoreException {
		doAction("delete", room);
	}

	/**
	 * Executes synchronized action in order to prevent inconsistent data
	 * @param action
	 * @param room
	 */
	private synchronized void doAction(String action, Room room) {
		try {
			if ("delete".equals(action))
				roomHandler.delete(room);
			else

			if ("create".equals(action))
				roomHandler.create(room);
			else if ("update".equals(action))
				roomHandler.update(room);

		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
	}

}
