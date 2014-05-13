package ch.zhaw.sqlbuilder.builders;

import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;

/**
 * Helper Methods for SQL Builders
 * 
 * @author Simon Aebersold
 * 
 */
public class SqlHelpers {

	/**
	 * Translator object for escaping queries.
	 */
	private static final CharSequenceTranslator ESCAPE_QUERY = new LookupTranslator(
			new String[][] { { "\"", "\\\"" }, { "\\", "\\\\" }, })
			.with(new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()));

	/**
	 * add quotes around strings and escapes bad characters, integers or
	 * floating point numbers are untouched. Helps against SQL-Injection. It's
	 * the next best thing to prepared statements.
	 * 
	 * @param str
	 * 
	 */
	public static String stringToStringQuoted(String str) {
		if (str.equals("NULL")) {
			return str;
		}

		try {
			Integer.parseInt(str);
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			// This makes a field SQL-Injection-Safe
			str = "\"" + ESCAPE_QUERY.translate(str) + "\"";
		}

		return str;
	}

	/**
	 * Converts an array to a comma separated string with quotes around strings.
	 * Please Note: Integers and floats don't get quotes.
	 * 
	 * @param sb
	 *            StringBuffer to work on
	 * @param arr
	 *            Array of Strings
	 */
	public static void arrayToCommaStringQuoted(StringBuilder sb, String[] arr) {

		for (int i = 0; i < arr.length; i++) {
			arr[i] = SqlHelpers.stringToStringQuoted(arr[i]);
		}

		SqlHelpers.arrayToCommaString(sb, arr);
	}

	/**
	 * Converts an array to a comma separated string, writes to given
	 * StringBuilder
	 * 
	 * @param sb
	 *            StringBuffer to work on
	 * @param arr
	 *            Array of Strings
	 */
	public static void arrayToCommaString(StringBuilder sb, String[] arr) {
		if (arr.length > 0) {
			int commaCounter = 1;
			for (String field : arr) {
				sb.append(field);

				if (commaCounter < arr.length) {
					sb.append(", ");
				}
				commaCounter++;
			}
		}
	}

}
