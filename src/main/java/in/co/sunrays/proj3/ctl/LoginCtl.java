package in.co.sunrays.proj3.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.RoleModelInt;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;


/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author Bridge
 *
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");
		System.out.println("loginctl vladiate started");

		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");

		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		log.debug("LoginCtl Method validate Ended");
		System.out.println("loginCtl validate");
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");

		UserDTO bean = new UserDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl Method populatebean Ended");
		System.out.println("loginCtl populate");

		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doGet Started");
		System.out.println("loginCtl doget startss");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_LOG_OUT.equals(op)) {
			System.out.println("loginCtl logout check");

			session = request.getSession();

			session.invalidate();
			
			
			System.out.println("doget logout");

			//ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
			//request.setAttribute("success", "Successfully Logout");
			ServletUtility.setSuccessMessage("Logout Successfully", request);
			ServletUtility.forward(getView(), request, response);
			
			return;

		} 
		
				// get model
		
		UserModelInt model = ModelFactory.getInstance().getUserModel();	
		RoleModelInt role = ModelFactory.getInstance().getRoleModel();

		System.out.println("loginctl--doget1");
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			UserDTO userbean;
			try {
				userbean = model.findByPK(id);
				ServletUtility.setDto(userbean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		System.out.println("loginctl--doget2");
		
		ServletUtility.forward(getView(), request, response);
		System.out.println("loginctl--doget3");
		
		log.debug("UserCtl Method doPost Ended");
		System.out.println("loginCtl doget ends");

	}

	/**
	 * Submitting or login action performing
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		RoleModelInt role = ModelFactory.getInstance().getRoleModel();
		
		System.out.println("loginCtl dopost");

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserDTO bean = (UserDTO) populateBean(request);
			System.out.println("loginCtl sign-in");

			try {

				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				System.out.println("loginctl--authenticated executed");
				if (bean != null) {
					session.setAttribute("user", bean);
					long rollId = bean.getRoleId();
					System.out.println("loginCtl sign-in check 4b");

					RoleDTO rolebean = role.findByPK(rollId);

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());
						System.out.println("loginCtl sign-in check 4c");

					}
					
					 //ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);

					System.out.println("loginCtl dopost ends check 5");

					String uri = request.getParameter("uri");

					if (uri == "null" || "null".equalsIgnoreCase(uri)) {
						ServletUtility.redirect(ORSView.WELCOME_CTL, request,
								response);
					} else {
						ServletUtility.redirect(uri, request, response);
					}

					
					return;
				} else {
					bean = (UserDTO) populateBean(request);
					ServletUtility.setDto(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_LOG_OUT.equals(op)) {
			System.out.println("loginCtl logout check");

			session = request.getSession();

			session.invalidate();
			System.out.println("dopost logout");

			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

			return;

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			
			System.out.println("dopost reg");


			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		System.out.println("loginCtl  login view");

		return ORSView.LOGIN_VIEW;
	}

}
