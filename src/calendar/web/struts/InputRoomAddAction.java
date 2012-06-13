package calendar.web.struts;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.core.controller.RoomCategoryController;
import calendar.core.controller.RoomController;
import calendar.core.entity.Room;
import calendar.core.entity.RoomCategory;
import calendar.core.exception.CoreException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InputRoomAddAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputRoomForm inputRoomForm = (InputRoomForm) form;
		
		ServletContext context = servlet.getServletConfig().getServletContext();
		RoomController roomController = (RoomController) context.getAttribute("roomController");
		RoomCategoryController roomCategoryController = (RoomCategoryController) context.getAttribute("roomCategoryController");
		
		Room room = new Room();
		
		HashMap<String, Object> filter = new HashMap<String, Object>();
		
		Long id = Long.parseLong(inputRoomForm.getRoomCategory());
		
		System.out.println("id " + id);
		
		filter.put("id", id);
		
		try {
			
			RoomCategory roomCategory = roomCategoryController.read(filter).get(0);
			
			room.setLocal(inputRoomForm.getLocal());
			room.setName(inputRoomForm.getName());
			room.setDescription(inputRoomForm.getDescription());
			room.setRoomCategory(roomCategory);
			
			roomController.create(room);
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//RoomCategory roomCategory = new RoomCategory(Integer.parseInt(inputRoomForm.getRoomCategory()), null);
		// ORIGINAL
//		
//		room.setRoomCategory(roomCategory);
//		try {
//			roomController.create(room);
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return mapping.findForward("failure");
//		}
		
		
		
	    return mapping.findForward("success");

	}
	
}