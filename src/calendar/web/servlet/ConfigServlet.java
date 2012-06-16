package calendar.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.ui.bean.UIBean;

/**
 * Servlet implementation class ConfigServlet
 */
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String defaultRedirect = "";
	
	public void init(ServletConfig config) throws ServletException {		
		if (null != config.getInitParameter("default-redirect"))
			defaultRedirect = (String)config.getInitParameter("default-redirect");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		String lang = request.getParameter("lang");
		
		UIBean ui = (UIBean) session.getAttribute("ui");
			
		if (ui != null && !lang.equals(ui.getLang()))
			ui.setLang(lang);

		String redirectTo = request.getHeader("Referer");
		
		if (redirectTo == null)
			redirectTo = defaultRedirect;

		response.sendRedirect(redirectTo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
