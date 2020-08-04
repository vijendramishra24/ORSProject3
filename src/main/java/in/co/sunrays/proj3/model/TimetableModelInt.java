package in.co.sunrays.proj3.model;


import java.util.Date;
import java.util.List;

import in.co.sunrays.proj3.dto.TimetableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;

/**
 * Interface Implementation of TimeTable Model
 * @author Bridge
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public interface TimetableModelInt {
	/**
	 * Find next PK of TimeTable
	 * 
	 * @throws DatabaseException
	 */
	//public Integer nextPK() throws DatabaseException;
	/**
	 * Add a TimeTable
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(TimetableDTO bean) throws ApplicationException, DuplicateRecordException ;
	/**
	 * Delete a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */
	public void delete(TimetableDTO bean) throws ApplicationException;
	/**
	 * Update a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * @throws @throws
	 *             DatabaseException
	 */

	public void update(TimetableDTO bean) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Find TimeTableDuplicacy
	 * 
	 * @throws ApplicationException
	 */
	//public TimetableDTO findTimeTableDuplicacy(Long courseId,String Semester, Date examdate) throws ApplicationException ;
	
	public TimetableDTO findDuplicate1(long courseId, String sem, long subjectId )throws ApplicationException;
	/**
	 * Find TimeTableDuplicacy
	 * 
	 * @throws ApplicationException
	 */
	//public TimetableDTO findTimeTableDuplicacy(Long courseId,String Semester, Long subjectId) throws ApplicationException;
	
	public TimetableDTO findDuplicate2(long courseId, String sem, String examTime ) throws ApplicationException;
	/**
	 * Find TimeTable by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	
	 */

	public TimetableDTO findByPK(long pk) throws ApplicationException ;
	
	
	/**
	 * Search TimeTable
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */

	public List search(TimetableDTO bean) throws ApplicationException ;
	
	
	
	/**
	 * Search TimeTable with pagination
	 * 
	 * @return list : List of TimeTable
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 * 
	 * @throws DatabaseException
	 */
	public List search(TimetableDTO bean, int pageNo, int PageSize) throws ApplicationException ;
	
	
	/**
	 * Get List of TimeTable
	 * 
	 * @return list : List of TimeTable
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException;
	
	/**
	 * Get List of TimeTable with pagination
	 * 
	 * @return list : List of TimeTable
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;
	
	
}
