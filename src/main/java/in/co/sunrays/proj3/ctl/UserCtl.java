package in.co.sunrays.proj3.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.RoleModelInt;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;


/**
 * User functionality Controller. Performs operation for add, update and get
 * User
 * 
 * @author Bridge
 *
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		System.out.println("UserCtl preload method");

		//RoleModel model = new RoleModel();
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;
		System.out.println("UserCtl Validate method");

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}

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
		//regex for password
			else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.pass", "Password "));
			pass = false;
		}
		

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {

			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (request.getParameter("gender").equals("---select---")) {
			request.setAttribute("gender", PropertyReader.getValue("error.select", "gender"));
			pass = false;
		}
		System.out.println("c3" + pass);
		if (request.getParameter("roleId").equals("---select---")) {
			request.setAttribute("roleId", PropertyReader.getValue("error.select", "Role"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}
		
		else if (!DataValidator.isMobile(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}
	

		/*
		 * if (request.getParameter("roleId").equals("Select")) {
		 * request.setAttribute("roleId",
		 * PropertyReader.getValue("error.select", "roleID")); pass = false; }
		 * System.out.println("c3"+pass);
		 */

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}
		//age validation
		else if(!DataValidator.isAge(dob)){
			request.setAttribute("dob", PropertyReader.getValue("error.age", "Age"));
			pass = false;
		}
		
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.cpass", "Confirm Password"));
			pass = false;
		}

		log.debug("UserCtl Method validate Ended");
		System.out.println("UserCtl valiadte ends");

		return pass;

	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		
		System.out.println("User Ctl populated Bean");

		log.debug("UserCtl Method populatebean Started");

		UserDTO bean = new UserDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateDTO(bean, request);

		log.debug("UserCtl Method populatebean Ended");

		
		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserCtl Ctl doGet");
		log.debug("UserCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("UserCtl doget id:"+id+" in id > 0  condition");
			UserDTO bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setDto(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				System.out.println("error fron doget UserCtl");
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doGet Ended");
		System.out.println("UserCtl doget ends");

	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost UserCtl ---starts");
		log.debug("UserCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		System.out.println("UserCtl dopost");

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			UserDTO bean = (UserDTO) populateBean(request);
			System.out.println("UserCtl dopost--save");

			try {
				if (id > 0) {
					System.out.println("UserCtl dopost--save update");
					model.update(bean);
				} else {
					System.out.println("UserCtl dopost--save add");
					long pk = model.add(bean);
					bean.setId(pk);
				}
				
				ServletUtility.setDto(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (ApplicationException e) {
				log.error(e);
				System.out.println("error fron dopost UserCtl--save");
				ServletUtility.handleException(e, request, response);
				return; 
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			UserDTO bean = (UserDTO) populateBean(request);
			try {
				System.out.println("UserCtl dopost--delete");
				model.delete(bean);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				System.out.println("error fron doget UserCtl --delete");
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		System.out.println("UserCtl getview");
		return ORSView.USER_VIEW;
	}

}
