package calendar.web.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import calendar.ui.bean.UIBean;

/**
 * Filters all requests and puts necessary information into the session
 * (for example Language information)
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class SessionFilter implements Filter {
	
	FilterConfig config;
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);
		
		ServletContext context = config.getServletContext();
		String resourcePath = (String) context.getAttribute("resourcePath");
		Locale locale = request.getLocale();
	    String language = locale.getLanguage();
		
		UIBean ui = (UIBean) session.getAttribute("ui");

		if (ui == null) {
			ui = new UIBean(resourcePath, language);
			session.setAttribute("ui", ui);
		}
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
