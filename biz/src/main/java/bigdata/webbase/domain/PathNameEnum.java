package bigdata.webbase.domain;

public enum PathNameEnum {
	//
	THEMES("/themes", ""),
	//
	CSS("/css", ""),
	//
	REGISTER("/register", ""),
	//
	JS("/js", ""),
	//
	LOGIN("/login", ""),
	//
	IMG("/img", ""),
	//
	GETVALIDATEIMAGE("/getValidateImage", ""),
	//
	ECHART("/echart", "echart报表"),
	//
	EXCEL("/excel", "excel导出"),
	//
	API("/api","智游宝票务系统API");
	
	private String value;
	private String name;

	private PathNameEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static boolean allInStr(String str) {
		PathNameEnum[] enums = PathNameEnum.values();
		for (PathNameEnum pathNameEnum : enums) {
			if (str.indexOf(pathNameEnum.getValue()) > -1)
				return true;
		}
		return false;
	}
}
