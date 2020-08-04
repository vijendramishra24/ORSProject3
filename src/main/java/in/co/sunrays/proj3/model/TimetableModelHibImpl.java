package in.co.sunrays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.TimetableDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;

public class TimetableModelHibImpl implements TimetableModelInt {
	private static Logger log = Logger.getLogger(TimetableModelHibImpl.class);

	/*public Integer nextPK() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}
*/
	public long add(TimetableDTO bean) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		TimetableDTO duplicateName = findDuplicate1(bean.getCourseId(), bean.getSem(), bean.getSubjectId());
		if (duplicateName != null) {			
			throw new DuplicateRecordException("time table already exists");
		}
		TimetableDTO duplicateTime = findDuplicate2(bean.getCourseId(), bean.getSem(), bean.getExamTime());
		
		if (duplicateTime != null) {
			System.out.println("duplicatTime");
			throw new DuplicateRecordException("time table already exists");
		}

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bean);
            pk = bean.getId();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in User Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");
        return bean.getId();
	}

	public void delete(TimetableDTO bean) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(bean);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in User Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
	}

	public void update(TimetableDTO bean) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		 log.debug("Model update Started");
	        Session session = null;
	        Transaction transaction = null;

	        TimetableDTO dtoExist = findDuplicate1(bean.getCourseId(), bean.getSem(), bean.getSubjectId());
	        // Check if updated LoginId already exist
	        if (dtoExist != null && dtoExist.getId() != bean.getId()) {
	            throw new DuplicateRecordException("LoginId is already exist");
	        }

	        try {
	            session = HibDataSource.getSession();
	            transaction = session.beginTransaction();
	            session.update(bean);
	            transaction.commit();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	                throw new ApplicationException("Exception in User Update"
	                        + e.getMessage());
	            }
	        } finally {
	            session.close();
	        }
	        log.debug("Model update End");
	}
/*
 * 
		log.debug("Method findTimeTableDuplicacy of Model TimeTable started");

		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			criteria.add(Restrictions.eq("semester", Semester));
			criteria.add(Restrictions.eq("examDate", examdate));
			List list = criteria.list();

			if (list.size() == 1) {
				dto = (TimeTableDTO) list.get(0);
			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception in getting TimeTbale by courseID, Semester, examDate " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findTimeTableDuplicacy End");
		return dto;
 *  */
	public TimetableDTO findDuplicate1(long courseId, String sem, long subjectId) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Method findTimeTableDuplicacy of Model TimeTable started");

		Session session = null;
		TimetableDTO bean = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			criteria.add(Restrictions.eq("sem", sem));
			criteria.add(Restrictions.eq("subjectId", subjectId));
			List list = criteria.list();

			if (list.size() == 1) {
				bean = (TimetableDTO) list.get(0);
			}
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception in getting TimeTbale by courseID, Semester, examDate " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findTimeTableDuplicacy End");
		return bean;
			
	}

	public TimetableDTO findDuplicate2(long courseId, String sem, String examTime) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Method findTimeTableDuplicacy of Model TimeTable started");

		Session session = null;
		TimetableDTO bean = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			criteria.add(Restrictions.eq("sem", sem));
			criteria.add(Restrictions.eq("examTime", examTime));
			List list = criteria.list();

			if (list.size() == 1) {
				bean = (TimetableDTO) list.get(0);
			}
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception in getting TimeTbale by courseID, Semester, examTime " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findTimeTableDuplicacy End");
		return bean;
	}

	public TimetableDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model findByPK Started");
        Session session = null;
        TimetableDTO bean = null;
        try {
            session = HibDataSource.getSession();
            bean = (TimetableDTO) session.get(TimetableDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return bean;
	}

	public List search(TimetableDTO bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(TimetableDTO bean, int pageNo, int PageSize) throws ApplicationException {
		 log.debug("Model search Started");
	        Session session = null;
	        List list = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(TimetableDTO.class);
	        

	            if (bean.getId() > 0) {
	                criteria.add(Restrictions.eq("id", bean.getId()));
	            }
	            if (bean.getCourseId() != 0 && bean.getCourseId() > 0) {
	                criteria.add(Restrictions.like("courseId", bean.getCourseId()
	                        + "%"));
	            }
	            if (bean.getSem() != null && bean.getSem().length() > 0) {
	                criteria.add(Restrictions.like("sem", bean.getSem()
	                        + "%"));
	            }
	            if (bean.getSubjectId() != 0 && bean.getSubjectId() > 0) {
	                criteria.add(Restrictions.like("subjectId", bean.getSubjectId() + "%"));
	            }
	            if (bean.getExamDate() != null && bean.getExamDate().getDate() > 0) {
	                criteria.add(Restrictions.like("examDate", bean.getExamDate()
	                        + "%"));
	            }
	            
	            // if page size is greater than zero the apply pagination
	            if (PageSize > 0) {
	                criteria.setFirstResult(((pageNo - 1) * PageSize));
	                criteria.setMaxResults(PageSize);
	            }

	            list = criteria.list();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException("Exception in user search");
	        } finally {
	            session.close();
	        }

	        log.debug("Model search End");
	        return list;

	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimetableDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Users list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }
	}
