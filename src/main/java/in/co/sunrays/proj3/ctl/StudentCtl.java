package in.co.sunrays.proj3.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.StudentModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;



/**
 *  Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * @author Bridge
 *
 */
@ WebServlet(name="StudentCtl",urlPatterns={"/ctl/StudentCtl"})
public class StudentCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		System.out.println("UserCtl preload method");

		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		
		//CollegeModel model = new CollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}


    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("StudentCtl Method validate Started");

        boolean pass = true;

        String op = DataUtility.getString(request.getParameter("operation"));
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");

        if (DataValidator.isNull(request.getParameter("firstName"))) {
            request.setAttribute("firstName",
                    PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("lastName"))) {
            request.setAttribute("lastName",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("mobileNo"))) {
            request.setAttribute("mobileNo",
                    PropertyReader.getValue("error.require", "Mobile No"));
            pass = false;
        }
        else if (!DataValidator.isMobile(request.getParameter("mobileNo"))) {
            request.setAttribute("mobileNo",
                    PropertyReader.getValue("error.require", "Mobile No"));
            pass = false;
        }
       
        if (DataValidator.isNull(email)) {
            request.setAttribute("email",
                    PropertyReader.getValue("error.require", "Email "));
            pass = false;
        } else if (!DataValidator.isEmail(email)) {
            request.setAttribute("email",
                    PropertyReader.getValue("error.email", "Email "));
            pass = false;
        }
               
        if (request.getParameter("collegeId").equals("---select---")) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.select", "College Name"));
			pass = false;
		}
        if (DataValidator.isNull(dob)) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        } else if (!DataValidator.isDate(dob)) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.date", "Date Of Birth"));
            pass = false;
        }//age validation
		else if(!DataValidator.isAge(dob)){
			request.setAttribute("dob", PropertyReader.getValue("error.age", "Age"));
			pass = false;
		}

        log.debug("StudentCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseDTO populateBean(HttpServletRequest request) {

        log.debug("StudentCtl Method populatebean Started");

        StudentDTO bean = new StudentDTO();

        bean.setId(DataUtility.getLong(request.getParameter("id")));

        bean.setFirstName(DataUtility.getString(request
                .getParameter("firstName")));

        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

        bean.setDob(DataUtility.getDate(request.getParameter("dob")));

        bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

        bean.setEmail(DataUtility.getString(request.getParameter("email")));

        bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

        populateDTO(bean, request);

        log.debug("StudentCtl Method populatebean Ended");

        return bean;
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("StudentCtl Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        long id = DataUtility.getLong(request.getParameter("id"));

        // get model

        //StudentModel model = new StudentModel();
        StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		
        if (id > 0 || op != null) {
        	StudentDTO bean;
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
        log.debug("StudentCtl Method doGett Ended");
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("StudentCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model

       // StudentModel model = new StudentModel();
        StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {

        	StudentDTO bean = (StudentDTO) populateBean(request);

            try {
                if (id > 0) {
                    model.update(bean);
                } else {
                    long pk = model.add(bean);
                    bean.setId(pk);
                }

                ServletUtility.setDto(bean, request);
                ServletUtility.setSuccessMessage("Data is successfully saved",
                        request);

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(bean, request);
                ServletUtility.setErrorMessage(
                        "Student Email Id already exists", request);
            }

        }

        else if (OP_DELETE.equalsIgnoreCase(op)) {

        	StudentDTO bean = (StudentDTO) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
                        response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility
                    .redirect(ORSView.STUDENT_LIST_CTL, request, response);
            return;

        }
        ServletUtility.forward(getView(), request, response);

        log.debug("StudentCtl Method doPost Ended");
    }

    @Override
    protected String getView() {
        return ORSView.STUDENT_VIEW;
    }


	

}
