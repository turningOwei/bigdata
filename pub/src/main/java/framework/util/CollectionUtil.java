package framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("rawtypes")
public class CollectionUtil {
	public static boolean isNotEmpty(Collection collections) {
		if (collections != null && collections.size() > 0)
			return true;
		return false;
	}

	public static boolean isEmpty(Collection collections) {
		if (collections == null || collections.size() == 0)
			return true;
		return false;
	}

	public static boolean innerElmentIsEmpty(Collection<Collection> collections) {
		if (isEmpty(collections))
			return true;
		for (Collection collection : collections) {
			if (isNotEmpty(collection))
				return false;
		}
		return false;
	}

	public static boolean innerElmentIsNotEmpty(
			Collection<Collection> collections) {
		if (isEmpty(collections))
			return false;
		for (Collection collection : collections) {
			if (isEmpty(collection))
				return false;
		}
		return true;
	}

	public static <T> List<T> mapToArrayList(Map<String, T> map) {
		List<T> list = new ArrayList<T>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			list.add(map.get(it.next()));
		}
		return list;
	}

	public static boolean sourcesHasPath(List<String> strs, String str) {
		boolean result = false;
		if (isNotEmpty(strs)) {
			for (String string : strs) {
				if (matchPath(string)) {
					String[] arr = string.split("\\?");
					if (arr[0].trim().equals(str))
						result = true;
				} else {
					if (string.trim().equals(str.trim()))
						result = true;
				}
			}
		}
		return result;
	}

	public static boolean matchPath(String str) {
		Pattern p = Pattern.compile(".*\\?.*");
		Matcher m = p.matcher(str);
		return m.matches();
	}

}
