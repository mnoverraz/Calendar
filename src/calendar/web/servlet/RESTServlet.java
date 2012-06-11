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
	private HashMap<String, String> paramMap;
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		paramMap =new HashMap<String, String>();
		createParameterMap(request.getParameterMap());
		this.method = "GET";
		proceed(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		paramMap =new HashMap<String, String>();
		createParameterMap(request.getParameterMap());
		this.method = "POST";
		proceed(request, response);
	}

	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		paramMap =new HashMap<String, String>();
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
		this.method = "PUT";
		proceed(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		paramMap =new HashMap<String, String>();
		createParameterMap(request.getParameterMap());
		this.method = "DELETE";
		proceed(request, response);
	}

	private void createParameterMap(Map<String, String[]> map) {
		Set<Entry<String, String[]>> set = map.entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = (Entry<String, String[]>) it
					.next();
			String paramName = entry.getKey();

			String[] paramValues = entry.getValue();

			paramMap.put(paramName, paramValues[0]);

		}
	}

	private void proceed(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		StringBuilder content = new StringBuilder();
		WebController<?> controller = null;

		String contentType = "";

		String format = null;
		boolean showState = true;

		String resource = null;
		HashMap<String, String> params = new HashMap<String, String>();
		Message message = new Message();

		try {
			Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();

			while (it.hasNext()) {
				String key = it.next().getKey();
				String value = paramMap.get(key);

				if (paramMap.containsKey("format"))
					format = value;
				else if ("resource".equals(key))
					resource = value;
				else if ("showState".equals(key))
					showState = Boolean.parseBoolean(value);
				else
					params.put(key, value);
			}

			if (("xml").equals(format))
				contentType = "text/xml";
			else
				contentType = "application/json";

			if (null == resource
					|| null == config.getServletContext()
							.getAttribute(resource))
				throw new Exception("Resource: '" + resource
						+ "'is not available");
			controller = (WebController<?>) config.getServletContext()
					.getAttribute(resource);
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

			response.setContentType(contentType);
			out.write(content.toString());
		}
	}

}
