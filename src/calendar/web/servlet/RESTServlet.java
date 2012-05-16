package calendar.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.core.controller.EventController;
import calendar.web.controller.WebEventController;
import calendar.web.controller.WebController;
import calendar.web.renderer.Renderer;
/**
 * Servlet implementation class CalendarServlet
 */
public class RESTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, WebController> controllers;
	private String method;
	
	public void init(ServletConfig config) 
			throws ServletException {
		controllers = new HashMap<String, WebController>();
		controllers.put("event", new WebEventController());
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
		this.method = "GET";
		proceed(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.method = "POST";
		proceed(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.method = "PUT";
		proceed(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.method = "DELETE";
		proceed(request, response);
	}
	
	private void proceed(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);	
        PrintWriter out = response.getWriter();
		StringBuilder content = new StringBuilder();
		WebController controller = null;
		

		
		String contentType = "";
		

		String format = request.getParameter("format");
		String ressource = request.getParameter("ressource");
		HashMap<String, String> params = new HashMap<String, String>();
			
		if (("xml").equals(format))
			contentType = "text/xml";
		else
			contentType = "application/json";
		
		if (null != ressource || !controllers.containsKey("ressource"))
			//content.append("error");
		controller = controllers.get(ressource);

		
		ArrayList<HashMap<String, Object>> rawContent = new ArrayList<HashMap<String, Object>>();
		if ("GET".equals(method)) 
			rawContent = controller.read(params);
		
		content.append(Renderer.toJSON(rawContent));
		
		response.setContentType(contentType);
		out.write(content.toString());
	}
	
}
