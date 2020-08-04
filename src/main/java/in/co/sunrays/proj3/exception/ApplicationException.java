package in.co.sunrays.proj3.exception;


/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author Bridge
 *
 */
public class ApplicationException extends Exception {
	
	/**
	 * : Error message
	 * @param msg
	 */
	public ApplicationException(String msg) {
        super(msg);
    }
}
