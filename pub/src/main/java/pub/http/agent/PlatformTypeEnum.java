package pub.http.agent;

public enum PlatformTypeEnum {
	ANDROID("android", "安卓手机"), IPHONE("iphone", "苹果手机");
	private String name;
	private String value;

	private PlatformTypeEnum(String value, String name) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
