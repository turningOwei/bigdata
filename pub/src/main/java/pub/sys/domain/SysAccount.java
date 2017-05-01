package pub.sys.domain;

import framework.util.EncryptUtil;

/**
 * 
 * @ClassName: SysAccount
 * @Description: 系统账户
 * @author weipeng 175408322@qq.com
 * @date 2016年2月18日 下午6:53:19
 *
 */
public class SysAccount {

	private String loginName;

	private String passWord;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isPassWordSame(SysAccount account) {
		return passWord.equals(account.passWord);
	}

	/**
	 * 密码加密
	 */
	public void encryptPass() {
		this.passWord = EncryptUtil.encrypt(this.passWord);
	}

}