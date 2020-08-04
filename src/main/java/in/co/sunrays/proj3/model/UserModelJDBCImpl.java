package in.co.sunrays.proj3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.util.EmailBuilder;
import in.co.sunrays.proj3.util.EmailMessage;
import in.co.sunrays.proj3.util.EmailUtility;
import in.co.sunrays.proj3.util.JDBCDataSource;

public class UserModelJDBCImpl implements UserModelInt {
	
	private static Logger log = Logger.getLogger(UserModelJDBCImpl.class);

	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_USER");
			System.out.println("user--findpk--1");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("user--findpk--2");
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
		System.out.println("user--findpk--3");
		return pk + 1;
	}// method closed nextPK

	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */
	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		System.out.println("UserModel add check1");

		Connection conn = null;
		int pk = 0;
		UserDTO existdto = findByLogin(dto.getLogin());

		if (existdto != null) {// && !(existdto.getId() == dto.getId())
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
					.prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, dto.getFirstName());
			pstmt.setString(3, dto.getLastName());
			pstmt.setString(4, dto.getLogin());
			pstmt.setString(5, dto.getPassword());
			pstmt.setDate(6, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(7, dto.getMobileNo());
			pstmt.setLong(8, dto.getRoleId());
			pstmt.setInt(9, dto.getUnSuccessfulLogin());
			pstmt.setString(10, dto.getGender());
			pstmt.setTimestamp(11, dto.getLastLogin());
			pstmt.setString(12, dto.getLocks());
			pstmt.setString(13, dto.getRegisteredIP());
			pstmt.setString(14, dto.getLastLoginIP());
			pstmt.setString(15, dto.getCreatedBy());
			pstmt.setString(16, dto.getModifiedBy());
			pstmt.setTimestamp(17, dto.getCreatedDatetime());
			pstmt.setTimestamp(18, dto.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}// closing method add

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void delete(UserDTO dto) throws ApplicationException {
		log.debug("Model Delete Strated");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);// begin transaction

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_USER WHERE ID=?");
			pstmt.setLong(1, dto.getId());
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
			throw new ApplicationException("Exception : Exception in delete User");
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
	 * @return dto
	 * @throws DatabaseException
	 */

	public UserDTO findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN=?");
		UserDTO dto = null;
		Connection conn = null;
		// System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLocks(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();// should be inside try: nothing between try and catch
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return dto;
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public UserDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID=?");
		UserDTO dto = null;
		Connection conn = null;
		System.out.println("user--findPK--1");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			System.out.println("user--findPK--2");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("user--findPK--3");
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLocks(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
				System.out.println("user--findPK--4");
			}
			rs.close();// nothing between try and catch
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return dto;
	}

	/**
	 * Update a user
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		System.out.println("UserModel update");
		Connection conn = null;

		UserDTO dtoExist = findByLogin(dto.getLogin());
		// Check if updated LoginId already exist
		if (dtoExist != null && !(dtoExist.getId() == dto.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_Locks=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, dto.getFirstName());
			pstmt.setString(2, dto.getLastName());
			pstmt.setString(3, dto.getLogin());
			pstmt.setString(4, dto.getPassword());
			pstmt.setDate(5, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(6, dto.getMobileNo());
			pstmt.setLong(7, dto.getRoleId());
			pstmt.setInt(8, dto.getUnSuccessfulLogin());
			pstmt.setString(9, dto.getGender());
			pstmt.setTimestamp(10, dto.getLastLogin());
			pstmt.setString(11, dto.getLocks());
			pstmt.setString(12, dto.getRegisteredIP());
			pstmt.setString(13, dto.getLastLoginIP());
			pstmt.setString(14, dto.getCreatedBy());
			pstmt.setString(15, dto.getModifiedBy());
			pstmt.setTimestamp(16, dto.getCreatedDatetime());
			pstmt.setTimestamp(17, dto.getModifiedDatetime());
			pstmt.setLong(18, dto.getId());
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
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search User
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(UserDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(UserDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND id = " + dto.getId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + dto.getFirstName() + "%'");
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + dto.getLastName() + "%'");
			}
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + dto.getLogin() + "%'");
			}
			if (dto.getPassword() != null && dto.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + dto.getPassword() + "%'");
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + dto.getDob());
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + dto.getMobileNo());
			}
			if (dto.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + dto.getRoleId());
			}
			if (dto.getUnSuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFUL_LOGIN = " + dto.getUnSuccessfulLogin());
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + dto.getGender() + "%'");
			}
			if (dto.getLastLogin() != null && dto.getLastLogin().getTime() > 0) {
				sql.append(" AND LAST_LOGIN = " + dto.getLastLogin());
			}
			if (dto.getRegisteredIP() != null && dto.getRegisteredIP().length() > 0) {
				sql.append(" AND REGISTERED_IP like '" + dto.getRegisteredIP() + "%'");
			}
			if (dto.getLastLoginIP() != null && dto.getLastLoginIP().length() > 0) {
				sql.append(" AND LAST_LOGIN_IP like '" + dto.getLastLoginIP() + "%'");
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
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLocks(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of User
	 * 
	 * @return list : List of User
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("User Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_USER");
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
				UserDTO dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLocks(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

	/**
	 * @param id
	 *            : long id
	 * @param old
	 *            password : String oldPassword
	 * @param new
	 *            password : String newPassword
	 * @throws DatabaseException
	 */

	public UserDTO authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
		UserDTO dto = null;
		Connection conn = null;
System.out.println("user--aunth--1");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			System.out.println("user--aunth--2");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("user--aunth--3");
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				System.out.println("user--aunth--3a");
				dto.setDob(rs.getDate(6));
				System.out.println("user--aunth--3b");
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLocks(rs.getString(12));
				System.out.println("user--aunth--3c");
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
				System.out.println("user--aunth--4");
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return dto;
	}

	/**
	 * Lock User for certain time duration
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean locks(String login) throws RecordNotFoundException, ApplicationException {
		log.debug("Service lock Started");
		boolean flag = false;
		UserDTO dtoExist = null;
		try {
			dtoExist = findByLogin(login);
			if (dtoExist != null) {
				dtoExist.setLocks(UserDTO.ACTIVE);
				update(dtoExist);
				flag = true;
			} else {
				throw new RecordNotFoundException("LoginId not exist");
			}
		} catch (DuplicateRecordException e) {
			log.error("Application Exception..", e);
			throw new ApplicationException("Database Exception");
		}
		log.debug("Service lock End");
		return flag;
	}

	/**
	 * Get User corresponding to a Role (Role ID)
	 * 
	 * @return List : User Role List
	 * @param dto
	 * @throws ApplicationException
	 */

	public List getRoles(UserDTO dto) throws ApplicationException {
		log.debug("Model get roles Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE role_Id=?");
		Connection conn = null;
		List list = new ArrayList();
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, dto.getRoleId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLocks(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model get roles End");
		return list;
	}

	/**
	 * @param id
	 *            : long id
	 * @param old
	 *            password : String oldPassword
	 * @param newpassword
	 *            : String newPassword
	 * @throws DatabaseException
	 */

	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		log.debug("model changePassword Started");
		boolean flag = false;
		UserDTO dtoExist = null;

		dtoExist = findByPK(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {// authentication
			dtoExist.setPassword(newPassword);
			try {
				update(dtoExist);
			} catch (DuplicateRecordException e) {
				log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", dtoExist.getLogin());
		map.put("password", dtoExist.getPassword());
		map.put("firstName", dtoExist.getFirstName());
		map.put("lastName", dtoExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExist.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		log.debug("Model changePassword End");
		return flag;

	}

	public UserDTO updateAccess(UserDTO dto) throws ApplicationException {
		return null;
	}

	/**
	 * Register a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when user already exists
	 */

	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		long pk = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}

	/**
	 * Reset Password of User with auto generated Password
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean resetPassword(UserDTO dto) throws ApplicationException {

		String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
		UserDTO userData = findByPK(dto.getId());
		userData.setPassword(newPassword);

		try {
			update(userData);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());
		map.put("firstName", dto.getFirstName());
		map.put("lastName", dto.getLastName());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
	}

	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		UserDTO userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");

		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);// it will
																	// create
																	// message
																	// to be
																	// sent.

		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SUNARYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		System.out.println("usermodel---beforesemamil");
		EmailUtility.sendMail(msg);// it will actually send the message
		flag = true;
		System.out.println("usermodel---aftersemamil");
		return flag;
	}

	

}
