package bigdata.webbase.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum RunMode {
	runMode("runMode", "运行模式"), DEV("dev", "开发环境"), PRO("pro", "生产环境");
	private String value;
	private String name;

	private RunMode(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * @Title: isPro
	 * @Description: 默认是生产模式,只有设置是开发模式才行
	 * @param @param runMode
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 */
	public static boolean isPro(String runMode) {
		if (StringUtils.isBlank(runMode) || runMode.equals(PRO.getValue()))
			return true;
		return false;
	}

}
