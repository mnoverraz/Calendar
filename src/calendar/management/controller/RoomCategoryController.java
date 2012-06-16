package calendar.management.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.NamingException;

import calendar.core.ejb.entity.RoomCategory;
import calendar.core.ejb.session.PersistException;
import calendar.core.ejb.session.RoomCategoryHandlerLocal;
import calendar.core.exception.CoreException;
import calendar.management.exception.SystemException;

public class RoomCategoryController extends Controller<RoomCategory> {
	
	private RoomCategoryHandlerLocal roomCategoryHandler;
	public RoomCategoryController(Context context) throws NamingException {
		super(context);
		this.roomCategoryHandler = (RoomCategoryHandlerLocal) context.lookup("calendarEAR/RoomCategoryBean/local");
	}

	@Override
	public void create(RoomCategory object) throws CoreException {
		try {
			roomCategoryHandler.create(object);
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
	}

	@Override
	public ArrayList<RoomCategory> read(HashMap<String, Object> filter)
			throws CoreException {
		ArrayList<RoomCategory> roomCategories = new ArrayList<RoomCategory>();
		try {
			roomCategories = (ArrayList<RoomCategory>) roomCategoryHandler.read(filter);
		} catch (PersistException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}		
		return roomCategories;
	}

	@Override
	public void update(RoomCategory object) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RoomCategory object) throws CoreException {
		// TODO Auto-generated method stub
		
	}

}
