package bigdata.webbase.domain;

/**
 * @ClassName: StatusInfoEntity
 * @Description: TODO(b-jui回调参数)
 * @author zhaowei 716517@qq.com
 * @date 2016年1月14日 上午11:47:58
 * 
 */
public class StatusInfoEntity {

	private int statusCode;
	private String message;
	private String tabid;
	private String dialogid;
	private String divid;
	private boolean closeCurrent;
	private String forward;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}

	public String getDialogid() {
		return dialogid;
	}

	public void setDialogid(String dialogid) {
		this.dialogid = dialogid;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public boolean isCloseCurrent() {
		return closeCurrent;
	}

	public void setCloseCurrent(boolean closeCurrent) {
		this.closeCurrent = closeCurrent;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	/**
	 * @ClassName: StatusInfoCodeType
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author zhaowei 716517@qq.com
	 * @date 2016年1月14日 下午1:19:27
	 * 
	 */
	public enum StatusInfoCodeType {
		/**
		 * @Fields ok : TODO(状态码200)
		 */
		ok(200),
		/**
		 * @Fields error : TODO(状态码300)
		 */
		error(300),
		/**
		 * @Fields timeout : TODO(状态码301)
		 */
		timeout(301);

		private int value;

		StatusInfoCodeType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
