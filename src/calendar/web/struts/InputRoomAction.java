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

import org.apache.coyote.http11.InputFilter;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InputRoomAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputRoomForm inputRoomForm = (InputRoomForm) form;
		ServletContext context = servlet.getServletConfig().getServletContext();
		
		/* The roomCategoryList was instanced and populated here but when 
		 * the RoomFormBean implemented the 'validate' method, tomcat complained with
		 * a JspException: Failed to obtain specified collection, although roomCategoryList
		 * wasn't null.
		 * 
		 * I discovered that the problem lay in the sequence in which the methods are executed.
		 * 
		 * 1. ActionForm.reset (initialise objects here)
		 * 2. ActionForm.validate (is validate is set to true in the action mapping)
		 * 3. Action.execute (finally)
		 * 
		 * So the whole roomCategoryList thing is now in the reset method of InputRoomAction
		 * 
		 */
		
		/* ORIGINAL */
//		RoomCategoryController roomCategoryController = (RoomCategoryController) context.getAttribute("roomCategoryController");
//		ArrayList<RoomCategory> roomCategories = null;
//		ArrayList<RoomCategoryData> roomCategoryList = new ArrayList<RoomCategoryData>();
//	    
//		try {
//			roomCategories = roomCategoryController.read(null);			
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return mapping.findForward("failure");
//		}
//		
//		for (RoomCategory rc : roomCategories) {
//			roomCategoryList.add(new RoomCategoryData(String.valueOf(rc.getId()), rc.getName()));
//		}
//		
//		inputRoomForm.setRoomCategoryList(roomCategoryList);
		/* --------------------------------------------------------------------------------------- */
		
		

		
		// if 'id' is specified, reload data in form
		String param = request.getParameter("id");
		RoomController roomController = (RoomController) context.getAttribute("roomController");
		if (param != null) {			
			try {
				Long id = Long.parseLong(param);
				HashMap<String, Object> filter = new HashMap<String, Object>();
				filter.put("id", id);
				Room room = roomController.read(filter).get(0);
				if (room != null) {
					inputRoomForm.setLocal(room.getLocal());
					inputRoomForm.setName(room.getName());
					inputRoomForm.setDescription(room.getDescription());
					inputRoomForm.setRoomCategory(String.valueOf(room.getRoomCategory().getId()));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
				
	    return mapping.findForward("success");

	}
	
}