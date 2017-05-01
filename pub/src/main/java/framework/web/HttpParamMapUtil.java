package framework.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理request参数工具
 * @author wang xp
 *
 */
public class HttpParamMapUtil {

	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		
		@SuppressWarnings("unchecked")
		Map<String, String[]> param = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : param.entrySet()) {
			String str = entry.getValue()[0];
			map.put(entry.getKey(), str.trim());
		}
		return map;
	}
}
