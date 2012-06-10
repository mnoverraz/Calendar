package calendar.web.struts;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import metier.Bottin;
//import metier.Contact;
//import metier.PersistException;
//import metier.Room;
import calendar.core.entity.Room;
//import metier.RoomManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ListRoomAction extends Action {

  public ActionForward execute(ActionMapping mapping,
      ActionForm form, HttpServletRequest request,
      HttpServletResponse response) {
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