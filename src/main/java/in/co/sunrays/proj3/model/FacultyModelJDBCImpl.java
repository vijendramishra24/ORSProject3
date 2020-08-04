package in.co.sunrays.proj3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;

/**
 * JDBC Implementation of FacultyModel
 * @author Bridge
 *
 */
public class FacultyModelJDBCImpl implements FacultyModelInt {
	
	private static Logger log = Logger.getLogger(FacultyModelJDBCImpl.class);

	/**
	 * Find next PK of user
	 * 
	 * @throws DatabaseException
	 */
	private long roleId;

	/**
	 * @return the roleId
	 */
	public long getRoleId() {

		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM st_faculty");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}// method closed nextPK

	/**
	 * Add a Faculty
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(FacultyDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		System.out.println("FacultyModel add check1");
		Connection conn = null;
		
		//IMPORTANT CONCEPT
				
				//CollegeModel cModel = new CollegeModel();	
				CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
				CollegeDTO collegeBean = cModel.findByPK(bean.getCollegeId());
				bean.setCollegeName(collegeBean.getName());
		
				//SubjectModel sModel = new SubjectModel();
				SubjectModelInt sModel = ModelFactory.getInstance().getSubjectModel();
				SubjectDTO subjectBean = sModel.findByPK(bean.getSubjectId());
				bean.setSubjectName(subjectBean.getName());
		
				//CourseModel crModel = new CourseModel();
				CourseModelInt crModel = ModelFactory.getInstance().getCourseModel();
				CourseDTO crBean = crModel.findByPK(bean.getCourseId());
				bean.setCourseName(crBean.getName());
				
				
				
		int pk = 0;
		FacultyDTO existbean = findByName(bean.getLogin());

		if (existbean != null) {// && !(existbean.getId() == bean.getId())
			// CHANGE : Checking duplicate record by login id of existing with
			// new record id(next pk)
			throw new DuplicateRecordException("Login Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();// getting value of auto generated next primary key
			// System.out.println(pk +"inModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO st_faculty VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			
			pstmt.setLong(7,bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setLong(9,bean.getSubjectId());
			pstmt.setString(10, bean.getSubjectName());
			pstmt.setLong(11,bean.getSubjectId());
			pstmt.setString(12, bean.getCourseName());
			
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}// closing method add

	/**
	 * Delete a faculty
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void delete(FacultyDTO bean) throws ApplicationException {
		log.debug("Model Delete Strated");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);// begin transaction

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM st_faculty WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();// end transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Delete Ended");
	}

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public FacultyDTO findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_faculty WHERE NAME=?");
		FacultyDTO bean = null;
		Connection conn = null;
		// System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				
				bean.setDob(rs.getDate(5));
				bean.setMobileNo(rs.getString(6));
				
				bean.setCollegeId(rs.getLong(7));
				bean.setCollegeName(rs.getString(8));
				bean.setSubjectId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setCourseId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

			}
			rs.close();// should be inside try: nothing between try and catch
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_faculty WHERE ID=?");
		FacultyDTO bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				
				bean.setDob(rs.getDate(5));
				bean.setMobileNo(rs.getString(6));
				
				bean.setCollegeId(rs.getLong(7));
				bean.setCollegeName(rs.getString(8));
				bean.setSubjectId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setCourseId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));
			}
			rs.close();// nothing between try and catch
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a user
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update(FacultyDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		System.out.println("FacultyModel update");
		Connection conn = null;

		//IMPORTANT CONCEPT
		//CollegeModel cModel = new CollegeModel();	
		CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO collegeBean = cModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		//SubjectModel sModel = new SubjectModel();
		SubjectModelInt sModel = ModelFactory.getInstance().getSubjectModel();
		SubjectDTO subjectBean = sModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());

		//CourseModel crModel = new CourseModel();
		CourseModelInt crModel = ModelFactory.getInstance().getCourseModel();
		CourseDTO crBean = crModel.findByPK(bean.getCourseId());
		bean.setCourseName(crBean.getName());

		
		
		FacultyDTO beanExist = findByName(bean.getLogin());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE st_faculty SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,DOB=?,MOBILE_NO=?,COLLEGE_ID=?,COLLEGE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,COURSE_ID=?,COURSE_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			
			
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setLong(6,bean.getCollegeId());
			pstmt.setString(7, bean.getCollegeName());
			pstmt.setLong(8,bean.getSubjectId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setLong(10,bean.getCourseId());
			pstmt.setString(11, bean.getCourseName());
			
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());

			pstmt.setLong(16, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Faculty
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(FacultyDTO bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Faculties
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(FacultyDTO bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_faculty WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME LIKE '" + bean.getCourseName()+"%'");
			}
			if (bean.getCourseId() != 0 && bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID ="+bean.getCourseId());
			}
			
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				bean = new FacultyDTO();
				
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				
				bean.setDob(rs.getDate(5));
				bean.setMobileNo(rs.getString(6));
				
				bean.setCollegeId(rs.getLong(7));
				bean.setCollegeName(rs.getString(8));
				bean.setSubjectId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setCourseId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Faculty
	 * 
	 * @return list : List of Faculty
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Facultys
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_Faculty");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FacultyDTO bean = new FacultyDTO();
				
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				
				bean.setDob(rs.getDate(5));
				bean.setMobileNo(rs.getString(6));
				
				bean.setCollegeId(rs.getLong(7));
				bean.setCollegeName(rs.getString(8));
				bean.setSubjectId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setCourseId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

				
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Facultys");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

	
	

}
