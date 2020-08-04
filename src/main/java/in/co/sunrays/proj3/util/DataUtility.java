package in.co.sunrays.proj3.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Data Utility class to format data from one format to another
 * 
 * @author Bridge
 *
 */
public class DataUtility {

	// FORMAT OF DATE AND TIME USED IN PROJECT
	/**
	 * Application Date Format
	 */
	public static final String APP_DATE_FORMAT = "yyyy/MM/dd";
	public static final String APP_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

	// OBJECT OF FORMTS USED IN PROJECT
	/**
	 * Date formatter
	 */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

	// METHOD TO TRIM THE EMPTY SPACE AT FRONT AND BACK OF INPUT
	/**
	 * Trims and trailing and leading spaces of a String
	 * 
	 * @param val
	 * @return
	 */
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return ""; // return val corrected;
		}

	}// closing getSting Method

	// METHOD TO CONVERT OBJECT INTO STRING for DATE
	/**
	 * Converts and Object to String
	 * 
	 * @param val
	 * @return
	 */
	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}// closing getStringDate method

	// METHOD TO CONVERT STRING INTO INTEGER
	/**
	 * 
	 * Converts String into Integer
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}// closing getInt method

	// METHOD TO CONVERT STRING TO LONGINTERGER
	/**
	 * Converts String into Long
	 * 
	 * @param val
	 * @return
	 */
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}// closing getLong method

	// METHOD TO CONVERT String INTO Date
	/**
	 * Converts String into Date
	 * 
	 * @param val
	 * @return
	 */
	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}// closing getDate method

	// METHOD TO CONVERT DATE TO STRING
	/**
	 * Converts Date into String
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
			return "";
		}
	}// closing getDateString method

	// METHOD TO DETERMINE DATE AFTER N DAYS
	// overloaded method
	/**
	 * Gets date after n number of days
	 * 
	 * @param Date
	 * @param day
	 * @return
	 */
	public static Date getDate(String Date, int day) {
		return null;
	}// closing getDate method

	// METHOD TO CONVERT STRING INTO TIME
	/**
	 * Converts String into Time
	 * 
	 * @param val
	 * @return
	 */
	public static Timestamp getTimeStamp(String val) {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}// closing getTimeStamp method

	// METHOD TO CONVERT LONG INTO TIME
	// overloaded method for long parameter
	/**
	 * @param l
	 * @return
	 */
	public static Timestamp getTimeStamp(long l) {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}// closing getTimeStamp method

	// METHOD TO GET CURRENT TIME
	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}// closing getCurrentTimestamp

	// METHOD TO CONVERT TIMESTAMP TO SEC(LONG)
	// overloaded method for parameter type Timestamp
	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
	
	//******************new added method*************
	//covert int to int
	
	
	
	
	
	
	//************************************************
	
		public static void main(String[] args) {
		System.out.println(getInt("124"));
	}

}// closing class
