package pub.permission.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.Resource;
import pub.permission.domain.Role;


/**
 * 组织成员dao层操作
 * 
 * @author wang xp
 * @date 2015-05-28
 */
public interface RoleDao {

	/**
	 * 根据用户id获取角色列表
	 */
	List<Role> getRoleList(Long userId);

	/**
	 * 根据用户id获取角色列表
	 */
	List<Role> getRoleList(String userId);

	/**
	 * 根据角色id获取资源列表
	 */
	List<Resource> getResourceByRole(Long roleId);

	/**
	 * 根据角色id获取资源列表
	 */
	List<Resource> getResourceByRole(String roleId);

	/**
	 * 删除角色
	 */
	Integer deleteRole(Role role) throws Throwable;

	Integer deleteUserRole(String roleId);

	Integer deleteRoleResource(String roleId);

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return Integer
	 * @throws Throwable
	 */
	Integer saveRole(Role role) throws Throwable;

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @throws Throwable
	 */
	Integer updateRole(Role role) throws Throwable;

	/**
	 * 根据id获取角色
	 * 
	 * @param id
	 * @return role
	 */
	Role getRoleById(String id);

	/**
	 * 给用户授权角色
	 * 
	 * @param userId
	 *            , roleId
	 * @return Integer
	 */
	Integer grantRoleToUser(String userId, String roleId);

	/**
	 * 取消用户角色
	 * 
	 * @param userId
	 *            , roleId
	 * @return Integer
	 */
	Integer removeRoleFromUser(String userId, String roleId);

	/**
	 * 给角色添加资源
	 * 
	 * @param userId
	 *            , roleId
	 * @return Integer
	 */
	Integer grantResourceToRole(Long roleId, Long[] resourceIds);

	/**
	 * 删除角色中的资源
	 * 
	 * @param roleId
	 *            , resourceId
	 * @return Integer
	 */
	Integer removeResourceFromRole(Long roleId, Long[] resourceIds);

	/**
	 * 获取角色列表
	 */
	List<Role> getRoleForList(int start, int size);

	/**
	 * 获取总角色数
	 */
	public int getTotalRole();

	public Page<Role> getRoles(PageRequest buildPageRequest);

	public List<Role> getUserRoleByIds(List<String> ids);
}
