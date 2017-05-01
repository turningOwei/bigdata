package datacenterbizapiexternal.datapush.domain;

public enum HttpStatus {
	OK("200", "成功", "成功");
	private String value;
	private String name;
	private String description;

	private HttpStatus(String value, String name, String description) {
		this.value = value;
		this.name = name;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
