package pub.permission.domain;

import org.springframework.data.annotation.Transient;

import framework.util.StringUtil;

/**
 * 角色
 * 
 * @date 2015-06-02
 */
public class Role {

	private String id;

	private String name;

	private String memo;
	@Transient
	private Boolean roleFlag;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(Boolean roleFlag) {
		this.roleFlag = roleFlag;
	}

	public RoleResult validate() {
		if (StringUtil.isEmpty(name)) {
			return RoleResult.NAMEISNULL;
		} else if (StringUtil.isEmpty(memo)) {
			return RoleResult.MEMOISNULL;
		} else {
			return RoleResult.SUCCESS;
		}
	}
}
