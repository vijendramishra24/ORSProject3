package in.co.sunrays.proj3.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class validates input data
 * 
 * @author Bridge
 *
 */
public class DataValidator {

	// METHOD TO DETERMINE INPUT PROVIDED IS NOT EMPTY
	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static Boolean isNull(String val) {
		if (val == null || (val.trim()).length() == 0) {
			return true;
		} else {
			return false;
		}
	}// closing isNull method

	// OPPOSOITE OF ABOVE METHOd rESULT
	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}// Closing isNotNull method

	// METHOD TO CHECK INPUT TYPE AS INTEGER OR NOT
	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}// closing isInteger method

	// METHOD TO INPUT IS LONG OR NOT
	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}// closing isLong method

	// METHOD TO DETERMINE INPUT IS EMAIL ID OR NOT USING REGULAR EXPRESSION
	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);// matching of regx
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}// Closing isemail method
	//regex for password
	
	
	public static boolean isMobile(String val) {

		String emailreg = "(0/91)?[6-9][0-9]{9}$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);// matching of regx
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isPassword(String val) {

		String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}$";
		/*
		 ^                # start-of-string :NotIncluded
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once :NotIncluded
		(?=\S+$)          # no whitespace allowed in the entire string : NOtIncluded
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
		 * 
		 */

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);// matching of regx
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}// Closing isemail method

	public static boolean isRollNo(String val) {

		//String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}$";
		
		String passreg="^([0-9]{4})([A-Z]{2})([0-9]{2})([A-Z]{2})([0-9]{2})$";
		/*
		 * 0827EC11MT18
		 String passreg="'\'d{4}[A-Z]{2}'\'d{2}[A-Z]{2}'\'d{2}$";
		 */

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);// matching of regx
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	

	//
	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}// Closing isDate method

	
	public static boolean isAge(String val) {

		Date d = null;
		int x=0;
		
		//current date in specific format
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String strdate =dateFormat.format(date);
		Date now =DataUtility.getDate(strdate);
		
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
			//Period.between(d, now).getYears();
			 
		  SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy");
			     
	      x= Integer.parseInt(simpleDateformat.format(now))- Integer.parseInt(simpleDateformat.format(d));  
			}
		return x>=18;
	}// Closing isAge method
	
	
	// CHECKING....
	/**
	 * Test above methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*System.out.println("Not Null 2 " + isNotNull("ABC"));
		System.out.println("Not Null 3 " + isNotNull(null));

		System.out.println("Not Null 4 " + isNull("123"));
		System.out.println("Not Null 4 " + isNull(null));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));

		System.out.println("Is long" + isLong(" "));
		System.out.println("Is long" + isLong("123"));

		System.out.println("Is email" + isEmail("vijendramishra88@gmail.com"));
		System.out.println("Is email" + isEmail("vijendramishra88gmail.com"));
		System.out.println("Is email" + isEmail("vijendramishra88@gmailcom"));
		System.out.println("Is email" + isEmail("vijendramishra88@gmail."));
*/	
		System.out.println("Is roll" + isRollNo("0827EC11MT18"));
		
		System.out.println("Is roll" + isRollNo("0837CS11BE45"));
		System.out.println("Is mobile" + isMobile("6977101"));
	}

}// closing class
