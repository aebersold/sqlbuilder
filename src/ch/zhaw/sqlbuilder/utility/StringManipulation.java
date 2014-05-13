package ch.zhaw.sqlbuilder.utility;

/**
 * Helper class for string manipulation purposes
 * 
 * @author Simon Aebersold
 * 
 */
public class StringManipulation {

	private StringManipulation() {
	}

	/**
	 * Removes forward- and backward slashes from given String.
	 * 
	 * @param str
	 * @return
	 */
	public static String removeSlashes(String str) {
		return str.replaceAll("[\\\\/]", "");
	}

}
