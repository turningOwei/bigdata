package pub.permission.domain;

public enum AddResourceResult {
	SUCCESS(""), SERVICE_ERR("业务层有错,请联系管理员");
	private String errMsg;

	private AddResourceResult(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
