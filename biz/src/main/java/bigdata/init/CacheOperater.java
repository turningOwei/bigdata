package bigdata.init;

import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @date 2014-9-23 下午4:03:34
 * @version V1.0
 */
@Component
public class CacheOperater {

	private static HashMap<String, String> userMap = new HashMap<String, String>();

	/***
	 * 启动时加载缓存的数据
	 */
	public static void initCacheDate() {

		for (int i = 0; i < 10; i++) {

			userMap.put("s" + i, "d" + i);
		}

	}

	public static HashMap<String, String> getUserMap() {
		return userMap;
	}

	public static void clearCacheDate() {
		userMap.clear();
	}
}
