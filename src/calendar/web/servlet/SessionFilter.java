package calendar.web.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import calendar.web.bean.UIBean;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);
		
		UIBean ui = (UIBean) session.getAttribute("ui");

		if (ui == null) {
			ui = new UIBean();
			session.setAttribute("ui", ui);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
