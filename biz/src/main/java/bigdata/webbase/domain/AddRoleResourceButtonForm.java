package bigdata.webbase.domain;

import java.util.List;

import pub.permission.domain.Resource;

public class AddRoleResourceButtonForm {
	private String id;

	private String roleId;

	private List<Resource> resources;
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

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
