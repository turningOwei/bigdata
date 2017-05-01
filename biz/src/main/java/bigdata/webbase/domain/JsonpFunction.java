package bigdata.webbase.domain;

public enum JsonpFunction {
	Jsonp("callback", "jsonp回调函数");
	private String functionName;
	private String description;

	private JsonpFunction(String functionName, String description) {
		this.functionName = functionName;

		this.description = description;
	}

	public String getFunctionName() {
		return functionName;
	}

	public String getDescription() {
		return description;
	}

}
