package in.co.sunrays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;

/**
 * Hibernate Implementation of MarksheetModel
 * 
 * @author Bridge
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class MarksheetModelHibImpl implements MarksheetModelInt {

	private static Logger log = Logger.getLogger(MarksheetModelHibImpl.class);

	/**
	 * Add a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */
	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");
		long pk = 0;
		MarksheetDTO duplicateMarksheetName = findByRollNo(dto.getRollNo());

		if (duplicateMarksheetName != null) {
			throw new DuplicateRecordException("Marksheet Name already exists");
		}

		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ApplicationException("Exception in Marksheet Add " + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model add End");
		return dto.getId();
	}

	/**
	 * Delete a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void delete(MarksheetDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.delete(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ApplicationException("Exception in Marksheet Delete" + e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model delete End");
	}

	/**
	 * Find User by Marksheet Name
	 * 
	 * @param collage
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {
		log.debug("Model findByName Started");
		Session session = null;
		MarksheetDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(MarksheetDTO.class);
			criteria.add(Restrictions.eq("rollNo", rollNo));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (MarksheetDTO) list.get(0);
			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findByName End");
		return dto;
	}

	/**
	 * Find Collage by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public MarksheetDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session = null;
		MarksheetDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (MarksheetDTO) session.get(MarksheetDTO.class, pk);
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Marksheet by pk");
		} finally {
			session.close();
		}

		log.debug("Model findByPK End");
		return dto;
	}

	/**
	 * Update a Collage
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Session session = null;
		Transaction transaction = null;

		MarksheetDTO dtoExist = findByRollNo(dto.getRollNo());

		// Check if updated Marksheet already exist
		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("Marksheet is already exist");
		}

		try {

			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.update(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
				throw new ApplicationException("Exception in Marksheet Update" + e.getMessage());
			}
		} finally {
			session.close();
		}
		log.debug("Model update End");
	}

	/**
	 * Searches Marksheets with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(MarksheetDTO.class);

			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
				criteria.add(Restrictions.like("rollNo", dto.getRollNo() + "%"));
			}

			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in Marksheet search");
		} finally {
			session.close();
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Search Marksheet
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(MarksheetDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Gets List of Marksheet
	 * 
	 * @return list : List of Marksheet
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheet
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(MarksheetDTO.class);

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in  Marksheet list");
		} finally {
			session.close();
		}

		log.debug("Model list End");
		return list;
	}

	/**
	 * get Merit List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List getMeritList(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model getMeritList Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();

			StringBuffer hql = new StringBuffer(
					"from MarksheetDTO where physics>33 and chemistry>33 and physics>33 order by (physics + chemistry + maths) desc");
			System.out.println("Page Number" + pageNo);
			System.out.println("Page Size" + pageSize);

			System.out.println("Pagination query" + hql.toString());
			Query query = session.createQuery(hql.toString());

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize);
				query.setFirstResult(pageNo);
				query.setMaxResults(pageSize);
			}

			System.out.println("8888888888888888888888888");
			list = query.list();

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model getMeritList End");
		return list;
	}

	/*
	 * public List getMeritList(int pageNo, int pageSize) throws
	 * ApplicationException { log.debug("Model getMeritList Started"); Session
	 * session = null; List list = null; try { session =
	 * HibDataSource.getSession();
	 * 
	 * StringBuffer hql = new StringBuffer(
	 * "from marksheetdto order by (PHYSICS + CHEMISTRY + MATHS) desc");
	 * 
	 * // if page size is greater than zero then apply pagination if (pageSize >
	 * 0) { pageNo = ((pageNo - 1) * pageSize) + 1; hql.append(" limit " +
	 * pageNo + "," + pageSize); }
	 * 
	 * System.out.println(hql.toString()); Query query =
	 * session.createQuery(hql.toString());
	 * System.out.println("check marksheetmodel--after query"); list =
	 * query.list();
	 * 
	 * } catch (Exception e) { log.error(e); throw new
	 * ApplicationException("Exception in  marksheet list" + e.getMessage()); }
	 * finally { session.close(); }
	 * 
	 * log.debug("Model getMeritList End"); return list; }
	 */

}
