package in.co.sunrays.proj3.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.sunrays.proj3.dto.DropdownList;


/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 * 
 * @author Bridge
 *
 */
public class HTMLUtility {

	/**
	 * Create HTML SELECT list from MAP paramters values
	 * 
	 * @param name
	 * @param selectedVal
	 * @param map
	 * @return
	 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select class='form-control' name='" + name + "'style=" + "width:200px;" + "'>");

		Set<String> keys = map.keySet();
		String select = "---select---";
		sb.append("<option value='" + select + "'>" + select + "</option>");
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * Create HTML SELECT List from List parameter
	 * 
	 * @param name
	 * @param selectedVal
	 * @param list
	 * @return
	 */
	public static String getList(String name, String selectedVal, List list) {

		Collections.sort(list);

		List<DropdownList> dd = (List<DropdownList>) list;

		StringBuffer sb = new StringBuffer(
				"<select class='form-control' name='" + name + "'style=" + "width:200px;" + "'>");

		String key = null;
		String select = "---select---";
		sb.append("<option value='" + select + "'>" + select + "</option>");
		String val = null;

		for (DropdownList obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

}
