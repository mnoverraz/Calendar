package calendar.web.struts;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.core.entity.Room;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddRoomAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
	  
		RoomForm roomForm = (RoomForm) form;
		
		try {
			
		} catch (Exception e) {
			return mapping.findForward("failure");
		}
/*
    RoomManager roomMgr = (RoomManager) servlet.getServletContext()
        .getAttribute("room_manager"); // check
    
    ArrayList<Room> rooms = null;
    
    try {
      rooms = roomMgr.getRooms();
    } catch (PersistException e) {
      e.printStackTrace();
    }
    
    request.setAttribute("rooms", rooms);
    return mapping.findForward("listing");
    */
		return mapping.findForward("success");
	}
}