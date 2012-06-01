package calendar.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import calendar.web.controller.WebController;
import calendar.web.renderer.Message;

/**
 * Servlet implementation class CalendarServlet
 */
public class RESTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String method;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
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
		//HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		StringBuilder content = new StringBuilder();
		WebController<?> controller = null;

		String contentType = "";

		String format = null;
		boolean showState = true;
		
		String ressource = null;
		HashMap<String, String> params = new HashMap<String, String>();
		Message message = new Message();

		try {
			@SuppressWarnings("unchecked")
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
				else if ("showState".equals(paramName))
					showState = Boolean.parseBoolean(paramValues[0]);
				else
					params.put(paramName, paramValues[0]);

			}

			if (("xml").equals(format))
				contentType = "text/xml";
			else
				contentType = "application/json";

			if (null == ressource || null == config.getServletContext().getAttribute(ressource))
				throw new Exception("Resource: '" + ressource
						+ "'is not available");
			controller = (WebController<?>) config.getServletContext().getAttribute(ressource);
			if ("GET".equals(method))
				message = (Message) controller.read(params);
			else if ("POST".equals(method))
				message = (Message) controller.update(params);
			else if ("PUT".equals(method))
				message = (Message) controller.create(params);
			else if ("DELETE".equals(method))
				message = (Message) controller.delete(params);
		} catch (Exception e) {
			HashMap<String, Object> error = new HashMap<String, Object>();
			error.put("error", e.getMessage());

			message.addElementToBody(error);
			message.state = false;
		} finally {
			content.append(message.toJSON(showState));

			response.setContentType(contentType);
			out.write(content.toString());
		}
	}

}
