package pub.http.agent;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserAgentUtil {
	/**
	 * @Title: getPlatForm
	 * @Description: TODO(暂时只支持安卓，iphone)
	 * @param @param userAgent
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	public static String getPlatForm(String userAgent) {
		String andriod = getPlatForm(userAgent,
				PlatformTypeEnum.ANDROID.getValue());
		if (StringUtils.isNotBlank(andriod)) {
			return andriod;
		}
		String iphone = getPlatForm(userAgent,
				PlatformTypeEnum.IPHONE.getValue());
		if (StringUtils.isNotBlank(andriod)) {
			return iphone;
		}
		return "";
	}

	public static String getPlatForm(String userAgent, String platForm) {
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher matcher = pattern.matcher(userAgent);
		if (matcher.find()) {
			String result = matcher.group(0);
			result = StringUtils.isEmpty(result) ? "" : result.toLowerCase();
			if (result.contains(platForm)) {
				return platForm;
			}
		}
		return null;
	}

	private static String[] browsers = { "MQQBrowser", "Opera", "UCWEB" };

	public static String getBrowser(String userAgent, String[] browsers) {
		List<String> list = Arrays.asList(browsers);
		for (String str : list) {
			if (userAgent.indexOf(str) > 0) {
				return str;
			}
		}
		return null;
	}

	public static String getBrowser(String userAgent) {
		return getBrowser(userAgent, browsers);
	}

	public static String getMobileModelType(String userAgent) {
		Pattern pattern = Pattern.compile(";\\s(\\S\\s?\\S)\\s(Build)?/");
		Matcher matcher = pattern.matcher(userAgent);
		String model = "";
		if (matcher.find()) {
			model = matcher.group(1).trim();
		}
		return model;
	}

}
