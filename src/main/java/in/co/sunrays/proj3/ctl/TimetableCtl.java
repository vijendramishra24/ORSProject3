package in.co.sunrays.proj3.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.TimetableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;
import in.co.sunrays.proj3.model.TimetableModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;


/**
 * Timetable functionality Controller. Performs operation for add, update and get
 * Timetable
 * @author Bridge
 *
 */
@WebServlet(name = "TimetableCtl", urlPatterns = { "/ctl/TimetableCtl" })
public class TimetableCtl extends BaseCtl{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TimetableCtl.class);
	
	@Override
	protected void preload(HttpServletRequest request) {
		System.out.println("TimetableCtl preload method");

		CourseModelInt cmodel = ModelFactory.getInstance().getCourseModel();
		try {
			List l1 = cmodel.list();
			request.setAttribute("courseList", l1);
		} catch (ApplicationException e) {
			log.error(e);
		}
		
		SubjectModelInt smodel = ModelFactory.getInstance().getSubjectModel();
		try {
			List l2 = smodel.list();
			request.setAttribute("subjectList", l2);
		} catch (ApplicationException e) {
			log.error(e);
		}


	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;
		System.out.println("TT Ctl Validate method");

		
		if (request.getParameter("courseId").equals("---select---")) {
			request.setAttribute("courseId", PropertyReader.getValue("error.select", "Course name"));
			pass = false;
		}

		if (request.getParameter("sem").equals("---select---")) {
			request.setAttribute("sem", PropertyReader.getValue("error.select", "Semester"));
			pass = false;
		}

		if (request.getParameter("subjectId").equals("---select---")) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.select", "Subject name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examTime"))) {
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam time"));
			pass = false;
		}
		if (request.getParameter("examTime").equals("---select---")) {
			request.setAttribute("examTime", PropertyReader.getValue("error.select", "Exam time"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Date Of Exam"));
			pass = false;
		}if (!DataValidator.isDate(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.date", "Date Of Exam"));
			pass = false;
		}
		
		log.debug("UserCtl Method validate Ended");
		System.out.println("UserCtl valiadte ends"+ pass);

		return pass;

	}


	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("TimeTableCtl Method populatebean Started");

		TimetableDTO bean = new TimetableDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		bean.setSem(DataUtility.getString(request.getParameter("sem")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));

		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));

		populateDTO(bean, request);

		log.debug("TimetableCtl Method populatebean Ended");

		//System.out.println("UserCtl populate check 4");

		return bean;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		
		TimetableModelInt model = ModelFactory.getInstance().getTimetableModel();
		
		//TimetableModel model = new TimetableModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("timetable Ctl doget 5:in id > 0  condition");
			TimetableDTO bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setDto(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				System.out.println("error fron doget TimetableCtl");
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("TimetableCtl Method doGet Ended");
		System.out.println("TimetableCtl doget check 5");

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		TimetableModelInt model = ModelFactory.getInstance().getTimetableModel();
		//System.out.println("UserCtl dopost check 6");

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			TimetableDTO bean = (TimetableDTO) populateBean(request);
			//System.out.println("UserCtl dopost--save check 6a");

			try {
				if (id > 0) {
					//System.out.println("UserCtl dopost--save check 6b");
					model.update(bean);
				} else {
					//System.out.println("UserCtl dopost--save check 6b-else");
					long pk = model.add(bean);
					bean.setId(pk);
				}
				//System.out.println("UserCtl dopost--save check 6c");
				ServletUtility.setDto(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (ApplicationException e) {
				log.error(e);
				//System.out.println("error fron dopost UserCtl--save");
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(bean, request);
				ServletUtility.setErrorMessage(" Timetable already exists", request);
			}
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimetableDTO bean = (TimetableDTO) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				//System.out.println("error fron doget UserCtl --delete");
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}

	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}
}
