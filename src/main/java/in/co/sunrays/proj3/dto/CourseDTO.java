package in.co.sunrays.proj3.dto;

import java.sql.Timestamp;

public class CourseDTO extends BaseDTO {
	
	
	/**
	 * Name of Course
	 */
	private String name;
	/**
	 * Description of Description of Course
	 */
	private String description;
    /**
     * Code of Course
     */
    private String courseCode;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	 public String getKey() {
	        return id + "";
	    }

	   
	    public String getValue() {
	        return name;
	    }

}
