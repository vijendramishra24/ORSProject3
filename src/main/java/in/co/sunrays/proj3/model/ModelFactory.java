package in.co.sunrays.proj3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Factory of Model classes
 *
 * @author Bridge
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */


public class ModelFactory {
	
	
	//private static ResourceBundle bundle = ResourceBundle.getBundle("in.co.sunrays.proj3.bundle.system");
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("bundle.system");
	
    private static final String DATABASE = bundle.getString("DATABASE");
    private static ModelFactory mFactory = null;
    /**
     * Cache of Model classes
     */
    private static HashMap modelCache = new HashMap();

    /**
     * Constructor is private so no other class can create instance of Model
     * Locator
     */
    private ModelFactory() {

    }

    /**
     * Get the instance of Model Locator
     *
     * @return mFactory
     */
    public static ModelFactory getInstance() {
        if (mFactory == null) {
            mFactory = new ModelFactory();
        }
        return mFactory;
    }

    /**
     * Get instance of Marksheet Model
     *
     * @return marksheetModel
     */
    public MarksheetModelInt getMarksheetModel() {
        MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache
                .get("marksheetModel");
        if (marksheetModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                marksheetModel = new MarksheetModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }
            modelCache.put("marksheetModel", marksheetModel);
        }

        return marksheetModel;
    }

    /**
     * Get instance of User Model
     *
     * @return userModel
     */
    public UserModelInt getUserModel() {
    	System.out.println("getUserModel--modelFactory--1");
        UserModelInt userModel = (UserModelInt) modelCache
                .get("userModel");
        System.out.println("getUserModel--modelFactory--1");
        if (userModel == null) {
            if ("Hibernate".equals(DATABASE)) {
            	System.out.println("getUserModel--modelFactory--hibet--1");
                userModel = new UserModelHibImpl();
                System.out.println("getUserModel--modelFactory--hibet--2");
            }
            if ("JDBC".equals(DATABASE)) {
                userModel = new UserModelJDBCImpl();
            }
            modelCache.put("userModel", userModel);
        }

        return userModel;
    }

    /**
     * Get instance of Collage Model
     *
     * @return collage
     */
    public CollegeModelInt getCollegeModel() {
        CollegeModelInt collegeModel = (CollegeModelInt) modelCache
                .get("collegeModel");
        if (collegeModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                collegeModel = new CollegeModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                collegeModel = new CollegeModelJDBCImpl();
            }
            modelCache.put("collegeModel", collegeModel);
        }

        return collegeModel;
    }

    /**
     * Get instance of Student Model
     *
     * @return Student
     */
    public StudentModelInt getStudentModel() {
        StudentModelInt studentModel = (StudentModelInt) modelCache
                .get("StudentModel");
        if (studentModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                studentModel = new StudentModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                studentModel = new StudentModelJDBCImpl();
            }
            modelCache.put("studentModel", studentModel);
        }

        return studentModel;
    }

    /**
     * Get instance of Role Model
     *
     * @return roleModel
     */
    public RoleModelInt getRoleModel() {
        RoleModelInt roleModel = (RoleModelInt) modelCache
                .get("roleModel");
        if (roleModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                roleModel = new RoleModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                roleModel = new RoleModelJDBCImpl();
            }
            modelCache.put("roleModel", roleModel);
        }

        return roleModel;

    }
    
    /**
     * Get instance of Course Model
     *
     * @return courseModel
     */
    public CourseModelInt getCourseModel() {
        CourseModelInt courseModel = (CourseModelInt) modelCache
                .get("courseModel");
        if (courseModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                courseModel = new CourseModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                courseModel = new CourseModelJDBCImpl();
            }
            modelCache.put("courseModel", courseModel);
        }

        return courseModel;
    }

    /**
     * Get instance of Subject Model
     *
     * @return subjectModel
     */
    public SubjectModelInt getSubjectModel() {
        SubjectModelInt subjectModel = (SubjectModelInt) modelCache
                .get("subjectModel");
        if (subjectModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                subjectModel = new SubjectModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                subjectModel = new SubjectModelJDBCImpl();
            }
            modelCache.put("subjectModel", subjectModel);
        }

        return subjectModel;
    }

    /**
     * Get instance of Faculty Model
     *
     * @return facultyModel
     */
    public FacultyModelInt getFacultyModel() {
    	FacultyModelInt facultyModel = (FacultyModelInt) modelCache
                .get("facultyModel");
        if (facultyModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                facultyModel = new FacultyModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                facultyModel = new FacultyModelJDBCImpl();
            }
            modelCache.put("facultyModel", facultyModel);
        }

        return facultyModel;
    }

    /**
     * Get instance of Timetable Model
     *
     * @return timetableModel
     */
    public TimetableModelInt getTimetableModel() {
    	TimetableModelInt timetableModel = (TimetableModelInt) modelCache
                .get("timetableModel");
        if (timetableModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                timetableModel = new TimetableModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                timetableModel = new TimetableModelJDBCImpl();
            }
            modelCache.put("timetableModel", timetableModel);
        }

        return timetableModel;
    }
    

}
