package calendar.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.core.controller.EventController;
import calendar.web.renderer.FullCalendarRenderer;
/**
 * Servlet implementation class CalendarServlet
 */
public class RESTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventController eventController;
	
	public void init(ServletConfig config) 
			throws ServletException {
		eventController = new EventController();
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RESTServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	
        PrintWriter out = response.getWriter();
		FullCalendarRenderer renderer = new FullCalendarRenderer();
		StringBuilder content = new StringBuilder();
		content.append(renderer.EventRerderer(eventController.getEvents()));
		
		String contentType = "";
		

		String format = request.getParameter("format");
			
		if (("xml").equals(format))
			contentType = "application/xml";
		else if (("html").equals(format)) 
			contentType = "application/html";
		else
			contentType = "application/json";

		
		response.setContentType(contentType);

		out.write(content.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
