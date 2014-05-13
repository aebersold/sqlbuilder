package ch.zhaw.sqlbuilder.validation;

public class DataTypeValidator {

	public static boolean validateInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
