package pub.sys.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import pub.user.domain.UserContext;


/**
 * 
 * @ClassName: SysUser
 * @Description: 系统用户
 * @author weipeng 175408322@qq.com
 * @date 2016年2月18日 上午10:57:52
 *
 */
public class SysUser {

	private String id;

	private SysAccount sysAccount;

	private String name;

	private Integer age;

	private String address;

	private String email;

	@DBRef
	private UserContext usrCtx;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserContext getUsrCtx() {
		return usrCtx;
	}

	public void setUsrCtx(UserContext usrCtx) {
		this.usrCtx = usrCtx;
	}

	public SysAccount getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(SysAccount sysAccount) {
		this.sysAccount = sysAccount;
	}

}
