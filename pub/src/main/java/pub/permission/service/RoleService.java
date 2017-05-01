package pub.permission.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.Resource;
import pub.permission.domain.Role;
import pub.permission.domain.RoleResult;


/**
 * 角色service层接口
 * 
 * @author wang xp
 * @date 2015-06-02
 */
public interface RoleService {

	/**
	 * 根据用户id获取角色列表
	 */
	List<Role> getRoleList(Long userId);

	/**
	 * 根据角色id获取资源列表
	 */
	List<Resource> getResourceByRole(Long roleId);

	/**
	 * 删除角色
	 */
	String deleteRole(Role role) throws Throwable;

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return true添加成功，false失败
	 * @throws Throwable
	 */
	RoleResult saveRole(Role role) throws Throwable;

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @throws Throwable
	 */
	RoleResult updateRole(Role role) throws Throwable;

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
	 * @return boolean
	 */
	boolean grantRoleToUser(String userId, String roleId) throws Throwable;

	/**
	 * 取消用户角色
	 * 
	 * @param userId
	 *            , roleId
	 * @return boolean
	 */
	boolean removeRoleFromUser(String userId, String roleId) throws Throwable;

	/**
	 * 给角色添加资源
	 * 
	 * @param  roleId
	 * @return boolean
	 */
	boolean grantResourceToRole(Long roleId, Long[] resourceIds)
			throws Throwable;

	/**
	 * 删除角色中的资源
	 * 
	 * @param roleId
	 *            , resourceId
	 * @return boolean
	 */
	boolean removeResourceFromRole(Long roleId, Long[] resourceIds)
			throws Throwable;

	/**
	 * 获取角色列表
	 */
	List<Role> getRoleForList(int start, int size);

	/**
	 * 获取总角色数
	 */
	int getTotalRole();

	public Page<Role> getRoles(PageRequest buildPageRequest);
}
