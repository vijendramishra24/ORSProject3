package in.co.sunrays.proj3.ctl;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.util.ServletUtility;



/**
 * Error functionality Controller. Performs operation for Show Welcome page
 * @author Bridge
 *
 */
@WebServlet(name = "ErrorCtl", urlPatterns = { "/ErrorCtl" })
public class ErrorCtl extends BaseCtl{

	
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ErrorCtl.class);
	/**
	 * Contains Display logic
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("error page doget");
		log.debug("ErrorCtl Method doGet Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl Method doGet Ended");

	}

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { log.debug(
	 * "ErrorCtl Method doGet Started");
	 * 
	 * ServletUtility.forward(ORSView.ERROR_VIEW, request, response);
	 * 
	 * log.debug("ErrorCtl Method doGet Ended"); }
	 * 
	 */ @Override
	protected String getView() {
		 System.out.println("error page getView");
		return ORSView.ERROR_VIEW;
	}

}
