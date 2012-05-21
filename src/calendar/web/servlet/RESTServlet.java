package calendar.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.core.application.ResourceRegistry;
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

	public void init(ServletConfig config) throws ServletException {
		ResourceRegistry registry = (ResourceRegistry)config.getServletContext().getAttribute("registry");
		controllers = new HashMap<String, WebController>();
		
		if (registry != null) {
			WebController eventController = new WebEventController(registry);
			controllers.put("event", eventController);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RESTServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.method = "GET";
		proceed(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.method = "POST";
		proceed(request, response);
	}

	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.method = "PUT";
		proceed(request, response);
	}

	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.method = "DELETE";
		proceed(request, response);
	}

	private void proceed(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		StringBuilder content = new StringBuilder();
		WebController controller = null;

		String contentType = "";

		String format = null;
		;
		String ressource = null;
		HashMap<String, String> params = new HashMap<String, String>();
		ArrayList<HashMap<String, Object>> rawContent = new ArrayList<HashMap<String, Object>>();
		try {
			Map<String, String[]> map = request.getParameterMap();

			Set<Entry<String, String[]>> set = map.entrySet();
			Iterator<Entry<String, String[]>> it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<String, String[]> entry = (Entry<String, String[]>) it
						.next();
				String paramName = entry.getKey();

				String[] paramValues = entry.getValue();

				if ("format".equals(paramName))
					format = paramValues[0];
				else if ("ressource".equals(paramName))
					ressource = paramValues[0];
				else
					params.put(paramName, paramValues[0]);

			}

			if (("xml").equals(format))
				contentType = "text/xml";
			else
				contentType = "application/json";

			if (null == ressource || !controllers.containsKey(ressource))
				throw new Exception("Resource: '" + ressource
						+ "'is not available");
			controller = controllers.get(ressource);
			if ("GET".equals(method))
				rawContent = controller.read(params);
			else if ("POST".equals(method))
				rawContent = controller.update(params);
			else if ("PUT".equals(method))
				rawContent = controller.create(params);
			else if ("DELETE".equals(method))
				rawContent = controller.delete(params);
		} catch (Exception e) {
			HashMap<String, Object> error = new HashMap<String, Object>();
			error.put("error", e.getMessage());
			rawContent.add(error);
		} finally {
			content.append(Renderer.toJSON(rawContent));

			response.setContentType(contentType);
			out.write(content.toString());
		}
	}

}
