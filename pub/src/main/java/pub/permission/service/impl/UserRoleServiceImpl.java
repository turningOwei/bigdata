package pub.permission.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import framework.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.dao.RoleDao;
import pub.permission.dao.UserRoleDao;
import pub.permission.domain.GrantRoleResult;
import pub.permission.domain.Role;
import pub.permission.domain.UserRole;
import pub.permission.service.UserRoleService;


public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RoleDao roleDao;


	public Page<Role> getUserRoleByUserId(String userId,
			PageRequest buildPageRequest) {
		Page<Role> roles = roleDao.getRoles(buildPageRequest);
		UserRole userRole = userRoleDao.getUserRoleByUserId(userId);
		if (userRole != null && userRole.getRoles() != null
				&& userRole.getRoles().size() > 0) {
			LinkedHashSet<Role> userRoles = userRole.getRoles();
			for (Role role : roles) {
				Iterator<Role> it = userRoles.iterator();
				while (it.hasNext()) {
					Role innerRole = it.next();
					if (role.getId().equals(innerRole.getId()))
						role.setRoleFlag(true);
				}
			}
		}

		return roles;
	}


	public GrantRoleResult grantRoleToUser(String[] ids, String[] noncheckIds,
			String userId) {
		try {
			UserRole userRole = userRoleDao.getUserRoleByUserId(userId);
			userRole = userRole == null ? new UserRole() : userRole;
			userRole.setUserId(userId);
			LinkedHashSet<Role> roles = userRole.getRoles() == null ? new LinkedHashSet<Role>()
					: userRole.getRoles();
			List<String> deleteRoleIds = noncheckIds == null ? null : Arrays
					.asList(noncheckIds);
			List<String> addRoleIds = this.getAddRoleIds(roles, ids);
			LinkedHashSet<Role> finalRoles = getFinalRoles(roles,
					deleteRoleIds, addRoleIds);
			userRole.setRoles(finalRoles);
			userRoleDao.saveUserRole(userRole);
		} catch (Exception e) {
			e.printStackTrace();
			return GrantRoleResult.SERVICE_ERR;
		}

		return GrantRoleResult.SUCCESS;
	}

	private LinkedHashSet<Role> getFinalRoles(LinkedHashSet<Role> roles,
			List<String> deleteRoleIds, List<String> addRoleIds) {
		List<Role> addRole = roleDao.getUserRoleByIds(addRoleIds);
		roles = deleteRoles(roles, deleteRoleIds);
		roles.addAll(addRole);
		return roles;
	}

	private LinkedHashSet<Role> deleteRoles(LinkedHashSet<Role> roles,
			List<String> deleteRoleIds) {
		if (CollectionUtil.isNotEmpty(deleteRoleIds)
				&& CollectionUtil.isNotEmpty(roles)) {
			List<Role> removeRoles = new ArrayList<Role>();
			for (Role role : roles) {
				if (deleteRoleIds.contains(role.getId()))
					removeRoles.add(role);
			}
			roles.removeAll(removeRoles);
		}
		return roles;
	}

	private List<String> getAddRoleIds(LinkedHashSet<Role> roles, String[] ids) {
		List<String> outLst = new ArrayList<String>();
		if (ids == null || ids.length == 0)
			return outLst;
		if (CollectionUtil.isEmpty(roles)) {
			return Arrays.asList(ids);
		}

		List<String> inLst = Arrays.asList(ids);
		List<String> roleIds = new ArrayList<String>();
		for (Role role : roles) {
			roleIds.add(role.getId());
		}
		for (String string : inLst) {
			if (!roleIds.contains(string))
				outLst.add(string);
		}
		return outLst;

	}


	public UserRole getUserRoleByUserId(String userId) {
		UserRole userRole = userRoleDao.getUserRoleByUserId(userId);
		return userRole;
	}
}
