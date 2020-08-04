package in.co.sunrays.proj3.dto;

import java.sql.Timestamp;

public class SubjectDTO extends BaseDTO{
	
	

	/**
	 * Name of Subject
	 */
	private String name;
	/**
	 * Description of subject
	 */
	private String description;
    /**
     * Code of Subject
     */
    private String subjectCode;
    /**
     * Course id to which subject belongs
     */
    private int courseId;
    


	
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


	public String getSubjectCode() {
		return subjectCode;
	}


	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public String getKey() {
        return id + "";
    }

   
    public String getValue() {
        return name;
    }

}
