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
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventController eventController;
	
	public void init(ServletConfig config) 
			throws ServletException {
		eventController = new EventController();
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	
		FullCalendarRenderer renderer = new FullCalendarRenderer();
		String json = renderer.EventRerderer(eventController.getEvents());
		
		
		response.setContentType("application/json");

        PrintWriter out = response.getWriter();

		out.write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
