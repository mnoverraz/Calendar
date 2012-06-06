package calendar.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.web.bean.UIBean;

/**
 * Servlet implementation class ConfigServlet
 */
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String lang = "fr";
	private String resourcePath;
	private String defaultRedirect = "";
	
	public void init(ServletConfig config) throws ServletException {
		if (null != config.getInitParameter("lang"))
			lang = (String)config.getInitParameter("lang");
		
		if (null != config.getInitParameter("resourcePath"))
			resourcePath = (String)config.getInitParameter("resourcePath");
		
		if (null != config.getInitParameter("default-redirect"))
			resourcePath = (String)config.getInitParameter("default-redirect");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		String rLang = request.getParameter("lang");
		
		UIBean ui = (UIBean) session.getAttribute("ui");
		
		if (null != rLang) 
			lang = rLang;
			
		if (ui == null) {
			ui = new UIBean(resourcePath, lang);
			session.setAttribute("ui", ui);
		}
		
		if (!lang.equals(ui.getLang()))
			ui.setLang(lang);
		
		session.setAttribute("ui", ui);

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
