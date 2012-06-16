package calendar.web.struts;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.core.ejb.entity.Room;
import calendar.core.exception.CoreException;
import calendar.core.exception.SystemException;
import calendar.management.controller.RoomController;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InputRoomRemoveAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		ServletContext context = servlet.getServletConfig().getServletContext();
		RoomController roomController = (RoomController) context.getAttribute("roomController");
		
		Long id = Long.parseLong(request.getParameter("id"));
		System.out.println(String.valueOf(id));
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