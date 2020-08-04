package in.co.sunrays.proj3.util;

import java.util.ResourceBundle;

/**
 * Read the property values from application properties file using Resource
 * Bundle
 * 
 * @author Bridge
 *
 */
public class PropertyReader {

	// creating resource bundle
	private static ResourceBundle rb = ResourceBundle.getBundle("bundle.system");

	// getting value corresponds to key from properties file
	/**
	 * Return value of key
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {

		String val = null;

		try {
			val = rb.getString(key);
		} catch (Exception e) {
			val = key;
		}

		return val;
	}

	// Replacing value with param corresponds to key
	// overloaded
	/**
	 * Gets String after placing param values
	 * 
	 * @param key
	 * @param param
	 * @return
	 */
	public static String getValue(String key, String param) {
		String msg = getValue(key);
		msg = msg.replace("{0}", param);
		return msg;
	}

	// getting params array for replacement with value
	// overloaded
	/**
	 * Gets String after placing params values
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getValue(String key, String[] params) {
		String msg = getValue(key);
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}

	/**
	 * Test method
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		String[] params = { "Roll No" };
		System.out.println(PropertyReader.getValue("error.require", params));
	}
}
