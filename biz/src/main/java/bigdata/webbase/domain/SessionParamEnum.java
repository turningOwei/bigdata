package bigdata.webbase.domain;

public enum SessionParamEnum {
	USER("user", "登录用户");
	private String value;
	private String description;

	private SessionParamEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

}
