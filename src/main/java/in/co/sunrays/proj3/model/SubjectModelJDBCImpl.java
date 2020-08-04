package in.co.sunrays.proj3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;

public class SubjectModelJDBCImpl implements SubjectModelInt {
	
private static Logger log = Logger.getLogger(SubjectModelJDBCImpl.class);
	
	// Find next PK(primary key)
	// throwing customize exception:DatabaseException
	/**
	 * Find next PK of Role
	 * @return next PK
	 * @throws DatabaseException
	 */
	public int nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
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

	// Add a subject
		// throwing customize exception:ApplicationException and DuplicateRecord
		// Exception
		/**
		 * Add a subject
		 * @param bean
		 * @return pk
		 * @throws ApplicationException
		 * @throws DuplicateRecordException
		 */
		public long add(SubjectDTO bean) throws ApplicationException, DuplicateRecordException {
			
			System.out.println("add method subject");
			log.debug("Subject add Started");
			Connection conn = null;
			int pk = 0;
			// need to create findByname method??
			SubjectDTO duplicateSubject = findByName(bean.getName());

			// checking if the Subject already exists
			if (duplicateSubject != null && !(duplicateSubject.getId() == bean.getId())) {
				throw new DuplicateRecordException("Subject already exists");
			}

			try {
				conn = JDBCDataSource.getConnection();
				pk = nextPK();
					
						// Fetch auto generated PK
				System.out.println("add method subject connection done");
				
				System.out.println(pk + " in ModelJDBC");
				
				conn.setAutoCommit(false);// transaction started need
				// conn.commit() to end transaction.
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
			
				pstmt.setInt(1, pk);
				pstmt.setString(2, bean.getName());
				pstmt.setString(3, bean.getDescription());
				pstmt.setString(4, bean.getSubjectCode());
				pstmt.setString(5, bean.getCreatedBy());
				pstmt.setString(6, bean.getModifiedBy());
				pstmt.setTimestamp(7, bean.getCreatedDatetime());
				pstmt.setTimestamp(8, bean.getModifiedDatetime());
				pstmt.setInt(9, bean.getCourseId());
				
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
			log.debug("Subject add End");
			return pk;
		}// closing add Subject method

		// Delete a subject
		// Throws customize exceptions: ApplicationException
		/**
		 * Delete a subject
		 * @param bean
		 * @throws ApplicationException
		 */
		public void delete(SubjectDTO bean) throws ApplicationException {
			log.debug("Model delete Started");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false); // Begin transaction
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
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
				throw new ApplicationException("Exception : Exception in delete subject");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model delete Ended");
		}

		// Method to find a subject by name "not by Subject"
		// returns the bean of subject
		// use customize exception :ApplicationException
		/**
		 * Find a subject by name
		 * @param name
		 * @return
		 * @throws ApplicationException
		 */
		public SubjectDTO findByName(String name) throws ApplicationException {
			log.debug("Model findBy Name Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE NAME=?");
			SubjectDTO bean = null;
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
					bean = new SubjectDTO();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setSubjectCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					//bean.setCreatedDatetime(rs.getTimestamp(7));
					//bean.setModifiedDatetime(rs.getTimestamp(8));
					bean.setCourseId(rs.getInt(9));
					System.out.println("check4");
					
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in getting subject by name");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model findBy name ended");
			System.out.println("check5");
			return bean;
			
		}

		// Method to find subject details by PK
		//
		/**
		 * find a subject by pk
		 * @param pk
		 * @return
		 * @throws ApplicationException
		 */
		public SubjectDTO findByPK(long pk) throws ApplicationException {
			log.debug("Model findByPK Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
			SubjectDTO bean = null;
			Connection conn = null;
			
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {

					bean = new SubjectDTO();
					
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setSubjectCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
					bean.setCourseId(rs.getInt(9));
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in getting subject by pk");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model findByPK End");
			return bean;
		}

		//Update the Subject
		/**
		 * Update a subject
		 * @param bean
		 * @throws ApplicationException
		 * @throws DuplicateRecordException
		 */
		public void update(SubjectDTO bean) throws ApplicationException, DuplicateRecordException {
			log.debug("Model update Started");
			Connection conn = null;

			SubjectDTO duplicataSubject = findByName(bean.getName());
			// Check if updated Subject already exist
			if (duplicataSubject != null && !(duplicataSubject.getId() != bean.getId())) {
				throw new DuplicateRecordException("subject already exists");
			}
			try {
				conn = JDBCDataSource.getConnection();

				conn.setAutoCommit(false); // Begin transaction
				PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_SUBJECT SET NAME=?,DESCRIPTION=?,SUBJECT_CODE=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,COURSE_ID=? WHERE ID=?");
				pstmt.setString(1, bean.getName());
				pstmt.setString(2, bean.getDescription());
				pstmt.setString(3, bean.getSubjectCode());
				pstmt.setString(4, bean.getCreatedBy());
				pstmt.setString(5, bean.getModifiedBy());
				pstmt.setTimestamp(6, bean.getCreatedDatetime());
				pstmt.setTimestamp(7, bean.getModifiedDatetime());
				pstmt.setInt(8, bean.getCourseId());
				pstmt.setLong(9, bean.getId());
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
				throw new ApplicationException("Exception in updating subject ");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model update End");
		}//ending update

		public List search(SubjectDTO bean) throws ApplicationException {
			return search(bean, 0, 0);
		}

		/**
		 * Search subject with pagination
		 * 
		 * @return list : List of Subjects
		 * @param bean
		 *            : Search Parameters
 		 * @param pageNo
		 *            : Current Page No.
		 * @param pageSize
		 *            : Size of Page
		 * 
		 * @throws DatabaseException
		 */
		public List search(SubjectDTO bean, int pageNo, int pageSize) throws ApplicationException {
			log.debug("Model search Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1");

			if (bean != null) {
				if (bean.getId() > 0) {
					sql.append(" AND id = " + bean.getId());
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" AND NAME like '" + bean.getName() + "%'");
				}
				if (bean.getDescription() != null && bean.getDescription().length() > 0) {
					sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
				}
				if (bean.getSubjectCode() != null && bean.getSubjectCode().length() > 0) {
					sql.append(" AND SUBJECT_CODE like '" + bean.getSubjectCode() + "%'");
				}			
				if (bean.getCourseId() > 0 ) {
					sql.append(" AND COURSE_ID = " + bean.getCourseId());
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
					bean = new SubjectDTO();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setSubjectCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					//bean.setCreatedDatetime(rs.getTimestamp(7));
					//bean.setModifiedDatetime(rs.getTimestamp(8));
					bean.setCourseId(rs.getInt(9));
					
					list.add(bean);
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in search Subject");
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
		 * Get List of subject with pagination
		 * 
		 * @return list : List of Subject
		 * @param pageNo
		 *            : Current Page No.
		 * @param pageSize
		 *            : Size of Page
		 * @throws DatabaseException
		 */

		public List list(int pageNo, int pageSize) throws ApplicationException {
			log.debug("Model list Started");
			ArrayList list = new ArrayList();
			StringBuffer sql = new StringBuffer("select * from ST_SUBJECT");
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
					SubjectDTO bean = new SubjectDTO();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setSubjectCode(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					
					bean.setModifiedBy(rs.getString(6));
					
					//bean.setCreatedDatetime(rs.getTimestamp(7));
					//bean.setModifiedDatetime(rs.getTimestamp(8));
					bean.setCourseId(rs.getInt(9));
					
					list.add(bean);
				}
				rs.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception : Exception in getting list of Subject");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}

			log.debug("Model list End");
			return list;

		}


}
