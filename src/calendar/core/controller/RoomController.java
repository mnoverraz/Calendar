package calendar.core.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.NamingException;

import calendar.core.entity.Room;
import calendar.core.exception.CoreException;
import calendar.core.exception.SystemException;
import calendar.core.session.PersistException;
import calendar.core.session.RoomHandlerLocal;

public class RoomController extends Controller<Room> {
	private RoomHandlerLocal roomHandler;
	public RoomController(Context context) throws NamingException {
		super(context);
		this.roomHandler = (RoomHandlerLocal) context.lookup("calendarEAR/RoomBean/local");
	}

	@Override
	public void create(Room room) throws CoreException {
		try {
			roomHandler.create(room);
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
		
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
	public void update(Room object) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Room object) throws CoreException {
		// TODO Auto-generated method stub
		
	}

}
