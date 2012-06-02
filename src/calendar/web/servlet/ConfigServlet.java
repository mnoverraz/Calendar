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
	
	private UIBean uiBean;

	public void init(ServletConfig config) throws ServletException {
		if (null != config.getServletContext().getAttribute("lang")) {
			lang = (String)config.getServletContext().getAttribute("lang");
		}
		if (null != config.getServletContext().getAttribute("resourcePath")) {
			resourcePath = (String)config.getServletContext().getAttribute("resourcePath");
		}
		uiBean = new UIBean(resourcePath, lang);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		lang = request.getParameter("lang");
		
		if (null != lang && !lang.equals(uiBean.getLang())) 
			uiBean = new UIBean(resourcePath, lang);
		
		session.setAttribute("ui", uiBean);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
