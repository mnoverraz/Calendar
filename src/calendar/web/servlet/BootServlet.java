package calendar.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import calendar.core.application.BootStrap;

public class BootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		BootStrap.init();
		System.out.println("init");
		config.getServletContext().setAttribute("registry", BootStrap.ResourceRegistry);
	}

}
