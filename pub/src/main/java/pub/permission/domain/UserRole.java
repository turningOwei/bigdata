package pub.permission.domain;

import java.util.LinkedHashSet;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class UserRole {
	private String id;
	private String userId;
	private String memo;
	@DBRef
	private LinkedHashSet<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LinkedHashSet<Role> getRoles() {
		return roles;
	}

	public void setRoles(LinkedHashSet<Role> roles) {
		this.roles = roles;
	}

}
