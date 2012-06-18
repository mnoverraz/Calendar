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
 * This action class is meant to be triggered before the load of
 * 'inputRoomForm.jsp' or 'inputRoomEdition.jsp'.
 * 
 * When a parameter 'id' is specified within the action, the 'execute' method
 * calls the room controller to get the corresponding room and set
 * the InputRoomForm action form bean to its values. So the input form is prefilled
 * for editing or cleared for a new entry, depending of the 'id' parameter.
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class InputRoomAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputRoomForm inputRoomForm = (InputRoomForm) form;
		ServletContext context = servlet.getServletConfig().getServletContext();
		
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
				SystemException se = new SystemException();
				se.detailInformation = e;
			} catch (CoreException e) {
				SystemException se = new SystemException();
				se.detailInformation = e;
			}
		}
				
	    return mapping.findForward("success");

	}
	
}