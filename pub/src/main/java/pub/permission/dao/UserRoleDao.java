package pub.permission.dao;

import pub.permission.domain.UserRole;

import java.util.List;


public interface UserRoleDao {

	public UserRole getUserRoleByUserId(String userId);

	public void saveUserRole(UserRole userRole);

	public List<UserRole> getUserRolesByUserId(String userId);
}
