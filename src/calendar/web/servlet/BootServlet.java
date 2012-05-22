package calendar.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import calendar.core.application.BootStrap;
import calendar.core.application.ResourceRegistry;
import calendar.core.controller.EventController;
import calendar.web.controller.WebEventController;

public class BootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		BootStrap.init();
		
		ResourceRegistry registry = BootStrap.ResourceRegistry;
		EventController eventController = (EventController)registry.getController("EventController");
		WebEventController webEventController = new WebEventController(eventController);
		config.getServletContext().setAttribute("event", webEventController);
	}

}
