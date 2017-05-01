package pub.permission.domain;

import java.util.LinkedHashSet;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class RoleResource {
	private String id;

	private String roleId;

	// 针对resource的button权限保存
	private LinkedHashSet<Resource> resources;
	// 用于关联resource 用于菜单展示
	@DBRef
	private LinkedHashSet<Resource> refResources;

	private String memo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LinkedHashSet<Resource> getResources() {
		return resources;
	}

	public void setResources(LinkedHashSet<Resource> resources) {
		this.resources = resources;
	}

	public LinkedHashSet<Resource> getRefResources() {
		return refResources;
	}

	public void setRefResources(LinkedHashSet<Resource> refResources) {
		this.refResources = refResources;
	}

}
