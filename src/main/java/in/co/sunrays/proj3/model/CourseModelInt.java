package in.co.sunrays.proj3.model;

import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;

public interface CourseModelInt {
	
	/**
     * Add a course
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when course already exists
     */
    public long add(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a course
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated course record is already exist
     */
    public void update(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a course
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(CourseDTO dto) throws ApplicationException;

    /**
     * Find course by login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     */
    public CourseDTO findByName(String name) throws ApplicationException;

    /**
     * Find course by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public CourseDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Course
     * 
     * @return list : List of Course
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(CourseDTO dto) throws ApplicationException;

    /**
     * Search Course with pagination
     * 
     * @return list : List of Course
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(CourseDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Get List of Course
     * 
     * @return list : List of Course
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * Get List of Course with pagination
     * 
     * @return list : List of Course
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;



}
