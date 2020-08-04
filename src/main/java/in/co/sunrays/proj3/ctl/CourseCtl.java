package in.co.sunrays.proj3.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;


/**
 * Course functionality Controller. Performs operation for add, update, delete
 * and get Course
 * 
 * @author Bridge
 *
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CourseCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Coursecode"))) {
			request.setAttribute("Coursecode", PropertyReader.getValue("error.require", "Course Code"));
			pass = false;
		}
		
		log.debug("CourseCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("CourseCtl Method populatebean Started");

		CourseDTO bean = new CourseDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		bean.setCourseCode(DataUtility.getString(request.getParameter("Coursecode")));

		populateDTO(bean, request);

		log.debug("CourseCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		
		//CourseModel model = new CourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			CourseDTO bean;
			try {

				bean = model.findByPK(id);
				ServletUtility.setDto(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("CourseCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		System.out.println("Course dopost");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			CourseDTO bean = (CourseDTO) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
				} else {
					System.out.println("course--dopost--add");
					long pk = model.add(bean);
					System.out.println("course--dopost--add--end");
					bean.setId(pk);
				}

				ServletUtility.setDto(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);

			} catch (ApplicationException e) {
				log.error(e);
				System.out.println("course--dopost--exceptionhandle");
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(bean, request);
				ServletUtility.setErrorMessage("Course already exists", request);
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;

		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("CourseCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {

		return ORSView.COURSE_VIEW;
	}

}
