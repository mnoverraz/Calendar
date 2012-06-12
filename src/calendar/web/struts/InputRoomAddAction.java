package calendar.web.struts;

import java.util.ArrayList;

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
    
		Room room = new Room();
		
		room.setLocal(inputRoomForm.getLocal());
		System.out.println(room.getLocal());
		room.setName(inputRoomForm.getName());
		System.out.println(room.getName());
		room.setDescription(inputRoomForm.getDescription());
		System.out.println(room.getDescription());
		//room.setLocal(inputRoomForm.getLocal());

			    
//		try {
//			roomController.create(room);
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return mapping.findForward("failure");
//		}
		
		
		//request.setAttribute("roomCategoryList", roomCategoryList);
	    return mapping.findForward("success");

	}
	
}