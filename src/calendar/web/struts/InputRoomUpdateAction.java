package calendar.web.struts;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.core.ejb.entity.Room;
import calendar.core.ejb.entity.RoomCategory;
import calendar.core.exception.CoreException;
import calendar.core.exception.SystemException;
import calendar.management.controller.RoomCategoryController;
import calendar.management.controller.RoomController;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InputRoomUpdateAction extends Action {

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
		filter.put("id", id);
		
		try {
			 
			RoomCategory roomCategory = roomCategoryController.read(filter).get(0);
			
			room.setId(Long.parseLong(inputRoomForm.getId()));
			room.setLocal(inputRoomForm.getLocal());
			room.setName(inputRoomForm.getName());
			room.setDescription(inputRoomForm.getDescription());
			room.setRoomCategory(roomCategory);
			
			roomController.update(room);
			
		} catch (CoreException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
		
	    return mapping.findForward("success");

	}
	
}