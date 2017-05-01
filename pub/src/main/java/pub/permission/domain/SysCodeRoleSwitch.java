package pub.permission.domain;

public enum SysCodeRoleSwitch {
	OPEN("true", "开放"), CLOSE("false", "不开放");
	private String value;
	private String description;

	private SysCodeRoleSwitch(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
