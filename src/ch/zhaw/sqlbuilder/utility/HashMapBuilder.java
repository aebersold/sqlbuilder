package ch.zhaw.sqlbuilder.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper Class for HashMaps
 * 
 * @author Simon Aebersold
 * 
 */
public class HashMapBuilder {

	/**
	 * Allows to build a HashMap the literal way. Usage: HashMap<String,String>
	 * data = HashMapBuilder.build("key1","value1","key2","value2");
	 * 
	 * @param Key
	 *            /Value Strings
	 * @return The HashMap
	 */
	public static Map<String, String> build(String... data) {
		Map<String, String> result = new HashMap<String, String>();

		if (data.length % 2 != 0) {
			throw new IllegalArgumentException("Odd number of arguments");
		}

		String key = null;
		Integer step = -1;

		for (String value : data) {
			step++;
			switch (step % 2) {
			case 0:
				if (value == null)
					throw new IllegalArgumentException("Null key value");
				key = value;
				continue;
			case 1:
				result.put(key, value);
				break;
			}
		}

		return result;
	}

}
