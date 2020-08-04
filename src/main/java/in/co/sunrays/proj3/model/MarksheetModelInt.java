package in.co.sunrays.proj3.model;

import java.util.List;

import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;

/**
 * Data Access Object of Marksheet
 * 
 * @author Bridge
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public interface MarksheetModelInt {

	/**
     * Add a Marksheet
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when Marksheet already exists
     */
    public long add(MarksheetDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Marksheet
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(MarksheetDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Marksheet
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(MarksheetDTO dto) throws ApplicationException;

    /**
     * Find Marksheet by Roll Number
     * 
     * @param rollNo
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException;

    /**
     * Find Marksheet by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public MarksheetDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Marksheet with pagination
     * 
     * @return list : List of Marksheet
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(MarksheetDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Marksheet
     * 
     * @return list : List of Marksheet
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(MarksheetDTO dto) throws ApplicationException;

    /**
     * Gets List of Marksheet
     * 
     * @return list : List of Marksheet
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of Marksheet with pagination
     * 
     * @return list : List of Marksheet
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;
    
    public List getMeritList(int pageNo, int pageSize) throws ApplicationException;


	
}
