package pub.permission.dao;

import pub.permission.domain.RoleResource;

import java.util.List;


public interface RoleResourceDao {

	public RoleResource getRoleResourceByRoleId(String roleId);

	public void saveRoleResource(RoleResource roleResource);

	public List<RoleResource> getRoleResourcesByRoleIds(List<String> roleIds);

}
