package pub.user.domain;


import pub.common.domain.BsnsServResult;

/**
 * 登录结果
 * 
 * @author zhaowei
 *
 */
public class LoginResult extends BsnsServResult {

	private Fail fail;

	/**
	 * 由于密码不正确而失败
	 */
	public void unSuccessByWrongPass() {
		fail = Fail.WRONGPASS;
		unSuccess();
	}

	/**
	 * 由于账号不存在而失败
	 */
	public void unSuccessByNoAccount() {
		fail = Fail.NOACCOUNT;
		unSuccess();
	}

	public Fail getFail() {
		return fail;
	}

	public void setFail(Fail fail) {
		this.fail = fail;
	}

	public enum Fail {
		/**
		 * 无此帐户
		 */
		NOACCOUNT,

		/**
		 * 密码不正确
		 */
		WRONGPASS
	}


	protected String getDefaultFailMsg() {
		return fail.toString();
	}

}