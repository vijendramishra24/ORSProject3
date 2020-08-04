package in.co.sunrays.proj3.ctl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.sunrays.proj3.util.ServletUtility;


/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * @author Bridge
 *
 */
@WebFilter(filterName = "FrontCtl", urlPatterns = { "/ctl/*","/doc/*" })
public class FrontController implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("front controller enter");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession(true);

		if (session.getAttribute("user") == null) {
			System.out.println("filter checking");
			request.setAttribute("message", "Your session has been expired. Please re-Login.");
			
			String uri = request.getRequestURI();
			

			request.setAttribute("targeturi", uri);

			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		} else {
			System.out.println("filter forwarding");
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig conf) throws ServletException {
	}

}
