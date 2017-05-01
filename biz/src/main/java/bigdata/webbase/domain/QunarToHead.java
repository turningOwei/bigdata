package bigdata.webbase.domain;

import java.security.Timestamp;

/**
 * @ClassName: QunarToHead
 * @Description: TODO(接口系统输入参数head)
 * @author zhaowei 716517@qq.com
 * @date 2015年12月30日 下午4:30:21
 * 
 */
public class QunarToHead {
	/**
	 * @Fields appKey : TODO(分销商认证key)
	 */
	private String appKey;
	/**
	 * @Fields salt : TODO(时间戳)
	 */
	private Timestamp salt;
	/**
	 * @Fields sign : TODO(数据签名，签名方法：md5(md5(secretK ey +appKey) + salt)
	 *         md5采用32位小写)
	 */
	private String sign;
	/**
	 * @Fields version : TODO(版本)
	 */
	private String version;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Timestamp getSalt() {
		return salt;
	}

	public void setSalt(Timestamp salt) {
		this.salt = salt;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
