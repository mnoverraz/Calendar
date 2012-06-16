package calendar.web.struts;

import java.util.ArrayList;

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

public class ListRoomAction extends Action {

	public ActionForward execute(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) {
		  
		ServletContext context = servlet.getServletConfig().getServletContext();
		RoomController roomController = (RoomController) context.getAttribute("roomController");
		
		ArrayList<Room> rooms = null;
	
		try {
			rooms = roomController.read(null);
			request.setAttribute("rooms", rooms);
		} catch (CoreException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;

			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	
	  }
	
}