package pub.common.domain;

/**
 * 一般的业务服务结果
 * 
 * @author zhaowei
 *
 */
public abstract class BsnsServResult {

	/**
	 * 是否成功.默认为成功
	 */
	protected boolean success = true;

	/**
	 * 系统错误
	 */
	protected Throwable sysError;

	/**
	 * 设为不成功
	 */
	public void unSuccess() {
		success = false;
	}

	/**
	 * 由于系统错误设为不成功
	 * 
	 * @param sysErr
	 */
	public void unSuccessBySysErr(Throwable sysErr) {
		unSuccess();
		sysError = sysErr;
	}

	/**
	 * 可用于显示的默认出错信息。<br/>
	 * 对于高级要求的出错信息显示，请用VO包装此对象，自行处理。
	 * 
	 * @return
	 */
	public String getDefaultErrorMsg() {
		if (sysError != null) {
			return sysError.getMessage();
		} else {
			return getDefaultFailMsg();
		}
	}

	/**
	 * 具体业务可用于显示的默认出错信息。<br/>
	 * 对于高级要求的出错信息显示，请用VO包装此对象，自行处理。
	 * 
	 * @return
	 */
	protected abstract String getDefaultFailMsg();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Throwable getSysError() {
		return sysError;
	}

	public void setSysError(Throwable sysError) {
		this.sysError = sysError;
	}

}
