package pub.permission.domain;

public enum RoleResult {
	/**
	 * SUCCESS，成功 NAMEISNULL，角色名称为空 MEMOISNULL，角色描述为空 UNKNOWNERR，未知错误
	 */
	SUCCESS(""), NAMEISNULL("角色名称为空"), MEMOISNULL("角色描述为空"), UNKNOWNERR("未知错误");
	private String errMsg;

	private RoleResult(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
