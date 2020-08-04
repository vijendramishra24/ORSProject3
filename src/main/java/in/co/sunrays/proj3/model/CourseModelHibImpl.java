package in.co.sunrays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;

public class CourseModelHibImpl implements CourseModelInt {

	private static Logger log = Logger.getLogger(UserModelHibImpl.class);

    /**
     * Add a User
     * 
     * @param dto
     * @throws DatabaseException
     * 
     */
    public long add(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.debug("Model add Started");
        long pk = 0;

        CourseDTO dtoExist = findByName(dto.getName());

        if (dtoExist != null) {
            throw new DuplicateRecordException("COurse is already exist");
        }

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
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
        return dto.getId();
    }

    /**
     * Delete a User
     * 
     * @param dto
     * @throws DatabaseException
     */
    
    public void delete(CourseDTO dto) throws ApplicationException {
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
            throw new ApplicationException("Exception in User Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }

    /**
     * Find User by Login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public CourseDTO findByName(String name) throws ApplicationException {
        log.debug("Model findByLoginId Started");
        Session session = null;
        CourseDTO dto = null;
        try {
        	System.out.println("usermodel: findByLogin--try1");
            session = HibDataSource.getSession();
            System.out.println("usermodel: findByLogin--try2");
            Criteria criteria = session.createCriteria(CourseDTO.class);
            System.out.println("usermodel: findByLogin--criteria");
            criteria.add(Restrictions.eq("name", name));
            System.out.println("usermodel: findByLogin--addRestriction");
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (CourseDTO) list.get(0);
            }
            System.out.println("usermodel: findByLogin--list fetched");
            
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting User by Login " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
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
    public CourseDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        CourseDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (CourseDTO) session.get(CourseDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Update a User
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void update(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

        CourseDTO dtoExist = findByName(dto.getName());

        // Check if updated LoginId already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("LoginId is already exist");
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
                throw new ApplicationException("Exception in User Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
    }

    /**
     * Searches User
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(CourseDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Searches Users with pagination
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
    public List search(CourseDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        System.out.println("in method search 1-->" + dto.getName());
        System.out.println("in method search 2->" + dto.getCourseCode());

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CourseDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                criteria.add(Restrictions.like("name", dto.getName()
                        + "%"));
            }
            
            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
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

    /**
     * Gets List of user
     * 
     * @return list : List of Users
     * @throws DatabaseException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * get List of User with pagination
     * 
     * @return list : List of Users
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
            Criteria criteria = session.createCriteria(CourseDTO.class);

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
