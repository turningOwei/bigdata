package pub.user.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class User {

	private String id;

	private Account account;

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

}
