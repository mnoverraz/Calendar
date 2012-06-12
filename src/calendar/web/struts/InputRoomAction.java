package calendar.web.struts;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.core.controller.RoomCategoryController;
import calendar.core.entity.RoomCategory;
import calendar.core.exception.CoreException;

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
		RoomCategoryController roomCategoryController = (RoomCategoryController) context.getAttribute("roomCategoryController");
    
		ArrayList<RoomCategory> roomCategories = null;
		ArrayList<RoomCategoryData> roomCategoryList = new ArrayList<RoomCategoryData>();
	    
		try {
			roomCategories = roomCategoryController.read(null);			
		} catch (CoreException e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		
		for (RoomCategory rc : roomCategories) {
			roomCategoryList.add(new RoomCategoryData(String.valueOf(rc.getId()), rc.getName()));
		}
		
		inputRoomForm.setRoomCategoryList(roomCategoryList);
		
	    return mapping.findForward("success");

	}
	
}