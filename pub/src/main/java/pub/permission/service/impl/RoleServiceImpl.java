package pub.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pub.entityref.dao.EntityRefStateDao;
import pub.entityref.domain.EntityInfo;
import pub.entityref.domain.EntityRefState;
import pub.entityref.service.RefStateManageService;
import pub.permission.dao.RoleDao;
import pub.permission.domain.Resource;
import pub.permission.domain.Role;
import pub.permission.domain.RoleEISG;
import pub.permission.domain.RoleResult;
import pub.permission.service.ResourceService;
import pub.permission.service.RoleService;

/**
 * 角色service层接口实现
 * 
 * @author wang xp
 * @date 2015-06-02
 */
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RefStateManageService refStateManageService;

	@Autowired
	private EntityRefStateDao entityRefStateDao;

	@Autowired
	private ResourceService resourceService;

	/**
	 * 根据用户id获取角色列表
	 */

	public List<Role> getRoleList(Long userId) {
		List<Role> roleList = roleDao.getRoleList(userId);
		return roleList;
	}

	/**
	 * 根据用户id获取角色列表
	 */

	public List<Resource> getResourceByRole(Long roleId) {
		List<Resource> resourceList = roleDao.getResourceByRole(roleId);
		return resourceList;
	}

	/**
	 * 删除角色
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteRole(Role role) throws Throwable {
		Integer n = roleDao.deleteRole(role);
		if (n > 0) {
			EntityRefState state = entityRefStateDao
					.getEntityRefStateByEntityInfo(new EntityInfo(role, role
							.getId().toString()));
			if (state == null || !state.hasRef()) {
				roleDao.deleteUserRole(role.getId());
				roleDao.deleteRoleResource(role.getId());
				return "";
			} else {
				return "DELFOBFORREFD";
			}
		} else {
			return "ERROR";
		}
	}

	/**
	 * 添加角色
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RoleResult saveRole(Role role) throws Throwable {
		RoleResult result = role.validate();
		if (result.equals(RoleResult.SUCCESS)) {
			Integer n = roleDao.saveRole(role);
			if (n > 0) {
				return RoleResult.SUCCESS;
			} else {
				return RoleResult.UNKNOWNERR;
			}
		} else {
			return result;
		}
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @throws Throwable
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RoleResult updateRole(Role role) throws Throwable {
		RoleResult result = role.validate();
		if (result.equals(RoleResult.SUCCESS)) {
			Integer n = roleDao.updateRole(role);
			if (n > 0) {
				return RoleResult.SUCCESS;
			} else {
				return RoleResult.UNKNOWNERR;
			}
		} else {
			return result;
		}
	}

	/**
	 * 根据id获取角色
	 */

	public Role getRoleById(String id) {
		Role role = roleDao.getRoleById(id);
		return role;
	}

	/**
	 * 给用户授权角色
	 * 
	 * @param userId
	 *            , roleId
	 * @return boolean
	 * @throws Throwable
	 */

	public boolean grantRoleToUser(String userId, String roleId)
			throws Throwable {
		boolean msg = roleDao.grantRoleToUser(userId, roleId) > 0 ? true
				: false;
		if (msg) {
			Role role = getRoleById(roleId);
			refStateManageService.increaseRefCountForEntity(new EntityInfo(
					role, role.getId().toString()));
		}
		return msg;
	}

	/**
	 * 取消用户角色
	 * 
	 * @param userId
	 *            , roleId
	 * @return boolean
	 * @throws Throwable
	 */

	public boolean removeRoleFromUser(String userId, String roleId)
			throws Throwable {
		boolean msg = roleDao.removeRoleFromUser(userId, roleId) > 0 ? true
				: false;
		if (msg) {
			Role role = getRoleById(roleId);
			refStateManageService.decreaseRefCountForEntity(new EntityInfo(
					role, role.getId().toString()));
		}
		return msg;
	}

	/**
	 * 给角色添加资源
	 * 
	 * @param  roleId
	 * @return boolean
	 * @throws Throwable
	 */

	public boolean grantResourceToRole(Long roleId, Long[] resourceIds)
			throws Throwable {
		boolean flag = roleDao.grantResourceToRole(roleId, resourceIds) > 0 ? true
				: false;
		if (flag) {
			List<Resource> resource = resourceService
					.getResourceByIdList(resourceIds);
			refStateManageService.increaseRefCountForEntityList(new RoleEISG(
					resource));
		}
		return flag;

	}

	/**
	 * 删除角色中的资源
	 * 
	 * @param roleId
	 *            , resourceId
	 * @return boolean
	 * @throws Throwable
	 */

	// @Transactional(propagation = Propagation.REQUIRED, rollbackFor =
	// Exception.class)
	public boolean removeResourceFromRole(Long roleId, Long[] resourceIds)
			throws Throwable {
		boolean msg = roleDao.removeResourceFromRole(roleId, resourceIds) > 0 ? true
				: false;
		if (msg) {
			List<Resource> resource = resourceService
					.getResourceByIdList(resourceIds);
			refStateManageService.decreaseRefCountForEntityList(new RoleEISG(
					resource));
		}
		return msg;
	}

	/**
	 * 获取角色列表
	 */

	public List<Role> getRoleForList(int start, int size) {
		return roleDao.getRoleForList(start, size);
	}

	/**
	 * 获取总角色数
	 */

	public int getTotalRole() {
		return roleDao.getTotalRole();
	}


	public Page<Role> getRoles(PageRequest buildPageRequest) {
		return roleDao.getRoles(buildPageRequest);
	}
}
