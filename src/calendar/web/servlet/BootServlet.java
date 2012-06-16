package calendar.web.servlet;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import calendar.core.init.BootStrap;
import calendar.core.init.ResourceRegistry;
import calendar.management.controller.EventController;
import calendar.management.controller.RoomCategoryController;
import calendar.management.controller.RoomController;
import calendar.web.controller.WebEventController;
import calendar.web.controller.WebRoomController;

public class BootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		try {
			BootStrap.init();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext context = config.getServletContext();
		ResourceRegistry registry = BootStrap.ResourceRegistry;
		
		EventController eventController = (EventController)registry.getController("EventController");
		WebEventController webEventController = new WebEventController(eventController);
		context.setAttribute("event", webEventController);
		
		RoomController roomController = (RoomController)registry.getController("RoomController");
		context.setAttribute("roomController", roomController);
		
		WebRoomController webRoomController = new WebRoomController(roomController);
		context.setAttribute("room", webRoomController);
		
		RoomCategoryController roomCategoryController = (RoomCategoryController)registry.getController("RoomCategoryController");
		context.setAttribute("roomCategoryController", roomCategoryController);
		
		String resourcePath = (String)config.getInitParameter("resource-path");
		context.setAttribute("resourcePath", resourcePath);
	}

}
