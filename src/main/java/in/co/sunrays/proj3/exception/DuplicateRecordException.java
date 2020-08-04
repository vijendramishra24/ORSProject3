package in.co.sunrays.proj3.exception;
/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * 
 * @author Mishra
 *
 */
public class DuplicateRecordException extends Exception {
	/**
	 * : Error message
	 * @param msg
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
