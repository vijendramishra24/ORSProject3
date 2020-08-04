package in.co.sunrays.proj3.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */

public class UserDTO extends BaseDTO  {
	/*
	private long id;
	private String createdBy;
	private String modifiedBy;
	private Timestamp createdDatetime;
	private Timestamp modifiedDatetime;

   
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

*/
	/**
     * Lock Active constant for User
     */
    public static final String ACTIVE = "Active";
    /**
     * Lock Inactive constant for User
     */
    public static final String INACTIVE = "Inactive";
    /**
     * First Name of User
     */
    private String firstName;
    /**
     * Last Name of User
     */
    private String lastName;
    /**
     * Login of User
     */
    private String login;
    /**
     * Password of User
     */
    private String password;
    /**
     * Confirm Password of User
     */
    private String confirmPassword;
    /**
     * Date of Birth of User
     */
    private Date dob;
    /**
     * MobielNo of User
     */
    private String mobileNo;
    /**
     * Role of User
     */
    private long roleId;
    /**
     * Number of unsuccessful login attempt
     */
    private int unSuccessfulLogin;
    /**
     * Gender of User
     */
    private String gender;
    /**
     * Last login timestamp
     */
    private Timestamp lastLogin;
    /**
     * User Lock
     */
    private String locks = INACTIVE;
    /**
     * IP Address of User from where User was registred.
     */
    private String registeredIP;
    /**
     * IP Address of User of his last login
     */
    private String lastLoginIP;

    /**
     * accessor
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public int getUnSuccessfulLogin() {
        return unSuccessfulLogin;
    }

    public void setUnSuccessfulLogin(int unSuccessfulLogin) {
        this.unSuccessfulLogin = unSuccessfulLogin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLocks() {
        return locks;
    }

    public void setLocks(String locks) {
        this.locks = locks;
    }

    public String getRegisteredIP() {
        return registeredIP;
    }

    public void setRegisteredIP(String registeredIP) {
        this.registeredIP = registeredIP;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    
  
    public String getKey() {
        return id + "";
    }

   
    public String getValue() {
        return firstName + " " + lastName;
    }

}
