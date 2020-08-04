package in.co.sunrays.proj3.dto;

import java.sql.Timestamp;

public class MarksheetDTO extends BaseDTO {
	
	
	
	/**
     * Rollno of Student
     */
    private String rollNo;
    /**
     * ID of Student
     */
    private long studentId;
    /**
     * Name of Student
     */
    private String name;
    /**
     * Physics marks of Student
     */
    private Integer physics;
    /**
     * Chemistry marks of Student
     */
    private Integer chemistry;
    /**
     * Mathematics marks of Student
     */
    private Integer maths;

	
	
	
	   
	    public String getRollNo() {
		return rollNo;
	}


	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}


	public long getStudentId() {
		return studentId;
	}


	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPhysics() {
		return physics;
	}


	public void setPhysics(Integer physics) {
		this.physics = physics;
	}


	public Integer getChemistry() {
		return chemistry;
	}


	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}


	public Integer getMaths() {
		return maths;
	}


	public void setMaths(Integer maths) {
		this.maths = maths;
	}


		public String getValue() {
	        return rollNo;
	    }

		 public String getKey() {
		        return id + "";
		    }

}
