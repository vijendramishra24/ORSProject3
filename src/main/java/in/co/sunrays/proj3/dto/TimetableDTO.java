package in.co.sunrays.proj3.dto;

import java.sql.Timestamp;
import java.util.Date;

public class TimetableDTO extends BaseDTO {
	
	
	/**
	 * Course Id in timetable
	 */
	private long courseId;
	/**
	 * Semester in timetable
	 */
	private String sem;
    /**
     * Subject Id in timetable
     */
    private long subjectId;
    /**
     * Date of Exam mention in timetable
     */
    private Date examDate;
    /**
     * Time of exam mention in timetable
     */
    private String examTime;
    

	
	 public long getCourseId() {
		return courseId;
	}


	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}


	public String getSem() {
		return sem;
	}


	public void setSem(String sem) {
		this.sem = sem;
	}


	public long getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}


	public Date getExamDate() {
		return examDate;
	}


	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}


	public String getExamTime() {
		return examTime;
	}


	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}


	public String getKey() {
	        return id + "";
	    }

	   
	    public String getValue() {
	        return examDate + " " + examTime;
	    }


}
