package in.co.sunrays.proj3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;

public class CourseModelJDBCImpl implements CourseModelInt {
	
	private static Logger log = Logger.getLogger(CourseModelJDBCImpl.class);
	 
	/* Find next PK of Role
	 * @return next PK
	 * @throws DatabaseException
	 */	
	public int nextPK() throws DatabaseException {
	
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM st_course");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception: Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}// closing nextpk method

	// Add a Course
		// throwing customize exception:ApplicationException and DuplicateRecord
		// Exception
		/**
		 * Add a Course
		 * @param bean
		 * @return pk
		 * @throws ApplicationException
		 * @throws DuplicateRecordException
		 */
		public long add(CourseDTO bean) throws ApplicationException, DuplicateRecordException {
			
			System.out.println("add method Course");
			log.debug("Course add Started");
			Connection conn = null;
			int pk = 0;
			// need to create findByname method??
			CourseDTO duplicateCourse = findByName(bean.getName());

			// checking if the Course already exists
			if (duplicateCourse != null && !(duplicateCourse.getId() == bean.getId())) {
				throw new DuplicateRecordException("Course already exists");
			}

			try {
				conn = JDBCDataSource.getConnection();
				pk = nextPK();
					
						// Fetch auto generated PK
				System.out.println("add method Course connection done");
				
				System.out.println(pk + " in ModelJDBC");
				
				conn.setAutoCommit(false);// transaction started need
				// conn.commit() to end transaction.
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO st_course VALUES(?,?,?,?,?,?,?,?)");
			
				pstmt.setInt(1, pk);
				pstmt.setString(2, bean.getName());
				pstmt.setString(3, bean.getDescription());
				pstmt.setString(4, bean.getCourseCode());
				pstmt.setString(5, bean.getCreatedBy());
				pstmt.setString(6, bean.getModifiedBy());
				pstmt.setTimestamp(7, bean.getCreatedDatetime());
				pstmt.setTimestamp(8, bean.getModifiedDatetime());
				
				
				pstmt.executeUpdate();
			
				conn.commit();// end transaction
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				log.error("Database Evception..", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception : add rollback evception" + ex.getMessage());
				}
				throw new ApplicationException("Exception : Evception in add Student");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Course add End");
			return pk;
		}// closing add Course method

		// Delete a Course
		// Throws customize exceptions: ApplicationException
		/**
		 * Delete a Course
		 * @param bean
		 * @throws ApplicationException
		 */
		public void delete(CourseDTO bean) throws ApplicationException {
			log.debug("Model delete Started");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false); // Begin transaction
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM st_course WHERE ID=?");
				pstmt.setLong(1, bean.getId());	
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();

			} catch (Exception e) {
				log.error("Database exception..", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception : Delete rollback exception" + ex.getMessage());
				}
				throw new ApplicationException("Exception : Exception in delete Course");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model delete Ended");
		}

		// Method to find a Course by name "not by Course"
		// returns the bean of Course
		// use customize exception :ApplicationException
		/**
		 * Find a Course by name
		 * @param name
		 * @return
		 * @throws ApplicationException
		 */
		public CourseDTO findByName(String name) throws ApplicationException {
			log.debug("Model findBy Name Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM st_course WHERE COURSE_NAME=?");
			CourseDTO bean = null;
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				System.out.println("check1");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				System.out.println("check2");

				ResultSet rs = pstmt.executeQuery();
				System.out.println("check3");

				while (rs.next()) {
					bean = new CourseDTO();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCourseCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
					
					System.out.println("check4");
					
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in getting Course by name");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model findBy name ended");
			System.out.println("check5");
			return bean;
			
		}

		// Method to find Course details by PK
		//
		/**
		 * find a Course by pk
		 * @param pk
		 * @return
		 * @throws ApplicationException
		 */
		public CourseDTO findByPK(long pk) throws ApplicationException {
			log.debug("Model findByPK Started");
			//System.out.println("PRimary key  = "+pk);
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_Course WHERE ID=?");
			CourseDTO bean = null;
			Connection conn = null;
			
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {

					bean = new CourseDTO();
					
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCourseCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
					
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in getting Course by pk");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model findByPK End");
			return bean;
		}

		//Update the Course
		/**
		 * Update a Course
		 * @param bean
		 * @throws ApplicationException
		 * @throws DuplicateRecordException
		 */
		public void update(CourseDTO bean) throws ApplicationException, DuplicateRecordException {
			log.debug("Model update Started");
			Connection conn = null;

			CourseDTO duplicataCourse = findByName(bean.getName());
			// Check if updated Course already exist
			if (duplicataCourse != null && !(duplicataCourse.getId() != bean.getId())) {
				throw new DuplicateRecordException("Course already exists");
			}
			try {
				conn = JDBCDataSource.getConnection();

				conn.setAutoCommit(false); // Begin transaction
				PreparedStatement pstmt = conn.prepareStatement("UPDATE st_course SET COURSE_NAME=?,DESCRIPTION=?,COURSE_CODE=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
				pstmt.setString(1, bean.getName());
				pstmt.setString(2, bean.getDescription());
				pstmt.setString(3, bean.getCourseCode());
				pstmt.setString(4, bean.getCreatedBy());
				pstmt.setString(5, bean.getModifiedBy());
				pstmt.setTimestamp(6, bean.getCreatedDatetime());
				pstmt.setTimestamp(7, bean.getModifiedDatetime());
				
				pstmt.setLong(8, bean.getId());
				pstmt.executeUpdate();
				conn.commit(); // End transaction
				pstmt.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
				}
				throw new ApplicationException("Exception in updating Course ");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model update End");
		}//ending update

		public List search(CourseDTO bean) throws ApplicationException {
			return search(bean, 0, 0);
		}

		/**
		 * Search Course with pagination
		 * 
		 * @return list : List of Courses
		 * @param bean
		 *            : Search Parameters
 		 * @param pageNo
		 *            : Current Page No.
		 * @param pageSize
		 *            : Size of Page
		 * 
		 * @throws DatabaseException
		 */
		public List search(CourseDTO bean, int pageNo, int pageSize) throws ApplicationException {
			log.debug("Model search Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_Course WHERE 1=1");

			if (bean != null) {
				if (bean.getId() > 0) {
					sql.append(" AND id = " + bean.getId());
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" AND COURSE_NAME like '" + bean.getName() + "%'");
				}
				if (bean.getDescription() != null && bean.getDescription().length() > 0) {
					sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
				}
				if (bean.getCourseCode() != null && bean.getCourseCode().length() > 0) {
					sql.append(" AND Course_CODE like '" + bean.getCourseCode() + "%'");
				}
				
			}

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				// Calculate start record index
				pageNo = (pageNo - 1) * pageSize;

				sql.append(" Limit " + pageNo + ", " + pageSize);
				// sql.append(" limit " + pageNo + "," + pageSize);
			}

			ArrayList list = new ArrayList();
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new CourseDTO();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCourseCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
					
					list.add(bean);
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in search Course");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}

			log.debug("Model search End");
			return list;
		}
		
		public List list() throws ApplicationException {
			return list(0, 0);
		}

		/**
		 * Get List of Course with pagination
		 * 
		 * @return list : List of Course
		 * @param pageNo
		 *            : Current Page No.
		 * @param pageSize
		 *            : Size of Page
		 * @throws DatabaseException
		 */

		public List list(int pageNo, int pageSize) throws ApplicationException {
			log.debug("Model list Started");
			ArrayList list = new ArrayList();
			StringBuffer sql = new StringBuffer("select * from ST_Course");
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
					CourseDTO bean = new CourseDTO();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCourseCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					
					bean.setModifiedBy(rs.getString(6));
					
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
					
					list.add(bean);
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in getting list of Course");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}

			log.debug("Model list End");
			return list;

		}


}
