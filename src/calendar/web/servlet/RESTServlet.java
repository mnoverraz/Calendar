package calendar.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import calendar.web.renderer.ExceptionRenderer;
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

	@SuppressWarnings("unchecked")
	private HashMap<String, String> createParameterMap(
			HttpServletRequest request) throws IOException {
		HashMap<String, String> paramMap = new HashMap<String, String>();

		Set<Entry<String, String[]>> set = request.getParameterMap().entrySet();

		if (request.getContentLength() > 0) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));

			String data = br.readLine();

			String[] temp;

			temp = data.split("&");

			for (int i = 0; i < temp.length; i++) {
				String[] param = ((String) temp[i]).split("=");
				String name = param[0];
				String value = "";

				if (param.length > 1)
					value = param[1];

				paramMap.put(name, value);
			}
		}
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = (Entry<String, String[]>) it
					.next();
			String paramName = entry.getKey();

			String[] paramValues = entry.getValue();

			paramMap.put(paramName, paramValues[0]);

		}
		return paramMap;
	}

	private void proceed(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		StringBuilder content = new StringBuilder();
		WebController<?> controller = null;

		String contentType = "";

		String format = null;
		boolean showState = true;

		String resource = null;

		Message message = new Message();

		try {

			HashMap<String, String> params = createParameterMap(request);
			if (params.containsKey("format")) {
				format = params.get("format");
				params.remove("format");
			}
			if (params.containsKey("resource")) {
				resource = params.get("resource");
				params.remove("resource");
			}
			if (params.containsKey("showState")) {
				showState = Boolean.parseBoolean(params.get("showState"));
				params.remove("showState");
			}

			if (("xml").equals(format))
				contentType = "text/xml";
			else
				contentType = "application/json";

			if (null == resource || null == config.getServletContext().getAttribute(resource))
				throw new Exception("Resource: '" + resource + "'is not available");
				controller = (WebController<?>) config.getServletContext().getAttribute(resource);
			if ("GET".equals(method))
				message = (Message) controller.read(params);
			else if ("POST".equals(method))
				message = (Message) controller.update(params);
			else if ("PUT".equals(method))
				message = (Message) controller.create(params);
			else if ("DELETE".equals(method))
				message = (Message) controller.delete(params);
		} catch (Exception e) {
			ExceptionRenderer exRenderer = new ExceptionRenderer(e);
			message = exRenderer.getMessage();
		} finally {
			content.append(message.toJSON(showState));
			response.setCharacterEncoding("UTF-8");
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.write(content.toString());
			out.close();
		}
	}

}
