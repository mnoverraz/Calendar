package calendar.web.struts;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.core.ejb.entity.Room;
import calendar.core.exception.CoreException;
import calendar.management.controller.RoomController;
import calendar.management.exception.SystemException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This action class is meant to be triggered by the 'inputRoomRemove.do' action.
 * An 'id' parameter matching an existing room must be given with the action.
 * 
 * It calls the room controller to remove the specified room the database.
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class InputRoomRemoveAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		ServletContext context = servlet.getServletConfig().getServletContext();
		RoomController roomController = (RoomController) context.getAttribute("roomController");
		
		Long id = Long.parseLong(request.getParameter("id"));
		HashMap<String, Object> filter = new HashMap<String, Object>();
		
		filter.put("id", id);
		
		try {
			Room room = roomController.read(filter).get(0);
			roomController.delete(room);
		} catch (CoreException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
		
	    return mapping.findForward("success");

	}
	
}