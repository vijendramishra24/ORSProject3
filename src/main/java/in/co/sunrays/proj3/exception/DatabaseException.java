package in.co.sunrays.proj3.exception;
/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author Bridge
 *
 */
public class DatabaseException extends Exception {
	/**
	 * : Error message
	 * @param msg
	 */
	public DatabaseException(String msg) {
		super(msg);
	}

}
