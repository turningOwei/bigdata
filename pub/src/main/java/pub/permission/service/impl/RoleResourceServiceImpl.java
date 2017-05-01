package pub.permission.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import framework.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import pub.permission.dao.ButtonDao;
import pub.permission.dao.ResourceDao;
import pub.permission.dao.RoleResourceDao;
import pub.permission.domain.AddResourceResult;
import pub.permission.domain.Button;
import pub.permission.domain.Resource;
import pub.permission.domain.RoleResource;
import pub.permission.service.RoleResourceService;


public class RoleResourceServiceImpl implements RoleResourceService {
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private ButtonDao buttonDao;


	public Page<Resource> getResourcesByRoleId(String roleId,
			PageRequest buildPageRequest) {
		Page<Resource> resources = resourceDao.getResources(buildPageRequest);
		RoleResource roleResource = roleResourceDao
				.getRoleResourceByRoleId(roleId);
		if (roleResource != null && roleResource.getResources() != null
				&& roleResource.getResources().size() > 0) {
			LinkedHashSet<Resource> roleResources = roleResource.getResources();
			roleResources.removeAll(Collections.singletonList(null));
			for (Resource resource : resources) {
				Iterator<Resource> it = roleResources.iterator();
				while (it.hasNext()) {
					Resource innerResource = it.next();
					if (resource.getId().equals(innerResource.getId()))
						resource.setResourceFlag(true);
				}
			}
		}
		return resources;
	}


	public AddResourceResult addResourceToRole(String[] ids,
			String[] noncheckIds, String roleId) {
		try {
			RoleResource roleResource = roleResourceDao
					.getRoleResourceByRoleId(roleId);
			roleResource = roleResource == null ? new RoleResource()
					: roleResource;
			roleResource.setRoleId(roleId);
			LinkedHashSet<Resource> resources = roleResource.getResources() == null ? new LinkedHashSet<Resource>()
					: roleResource.getResources();

			List<String> deleteResourceIds = noncheckIds == null ? null
					: Arrays.asList(noncheckIds);
			List<String> addResourceIds = this.getAddResorceIds(resources, ids);
			LinkedHashSet<Resource> finalResources = getFinalResources(
					resources, deleteResourceIds, addResourceIds);

			roleResource.setResources(finalResources);
			roleResourceDao.saveRoleResource(roleResource);
		} catch (Exception e) {
			e.printStackTrace();
			return AddResourceResult.SERVICE_ERR;
		}

		return AddResourceResult.SUCCESS;
	}

	private LinkedHashSet<Resource> getFinalResources(
			LinkedHashSet<Resource> resources, List<String> deleteResourceIds,
			List<String> addResourceIds) {
		List<Resource> addResources = new ArrayList<Resource>();
		if (CollectionUtil.isNotEmpty(addResourceIds))
			addResources = resourceDao.getResourceByIds(addResourceIds);
		resources = deleteResources(resources, deleteResourceIds);
		if (CollectionUtil.isNotEmpty(addResources))
			resources.addAll(addResources);
		return resources;
	}

	private LinkedHashSet<Resource> deleteResources(
			LinkedHashSet<Resource> resources, List<String> deleteResourceIds) {
		if (CollectionUtil.isNotEmpty(deleteResourceIds)
				&& CollectionUtil.isNotEmpty(resources)) {
			List<Resource> removeResources = new ArrayList<Resource>();
			for (Resource resource : resources) {
				if (deleteResourceIds.contains(resource.getId()))
					removeResources.add(resource);
			}
			resources.removeAll(removeResources);
		}
		return resources;
	}

	private List<String> getAddResorceIds(LinkedHashSet<Resource> resources,
			String[] ids) {
		resources.removeAll(Collections.singleton(null));
		if (ids == null || ids.length == 0)
			return null;
		List<String> inLst = Arrays.asList(ids);
		if (CollectionUtil.isEmpty(resources))
			return inLst;
		for (int i = 0; i < inLst.size(); i++) {
			for (Resource resource : resources) {
				if (!inLst.contains(resource.getId()))
					inLst.remove(resource.getId());
			}
		}
		return inLst;
	}


	public Page<Resource> getResourcesByNameLike(String name,
			PageRequest buildPageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Resource> getResourcesByRoleId(String roleId) {
		Order order = new Order(Direction.ASC, "disOrder");
		Sort sort = new Sort(order);
		List<Resource> allResources = resourceDao.getAllResourcesSort(sort);
		RoleResource roleResource = roleResourceDao
				.getRoleResourceByRoleId(roleId);
		LinkedHashSet<Resource> roleResources = roleResource == null ? null
				: roleResource.getResources();
		allResources = addResourceFlag(allResources, roleResources);
		return allResources;
	}

	private List<Resource> addResourceFlag(List<Resource> allResources,
			LinkedHashSet<Resource> roleResources) {
		if (CollectionUtil.isEmpty(allResources))
			return null;
		if (CollectionUtil.isEmpty(roleResources))
			return allResources;
		for (Resource roleRes : roleResources) {
			for (Resource resource : allResources) {
				if (roleRes.getId().equals(resource.getId())) {
					resource.setResourceFlag(true);
					resource = addButtonCheckFlag(resource, roleRes);
				}
			}
		}
		return allResources;
	}

	private Resource addButtonCheckFlag(Resource resource, Resource roleRes) {
		List<Button> resButtons = resource.getButton();
		List<Button> roleResButton = roleRes.getButton();
		if (CollectionUtil.isEmpty(roleResButton))
			return resource;
		for (Button roleButton : roleResButton) {
			for (Button button : resButtons) {
				if (roleButton != null
						&& roleButton.getId().equals(button.getId()))
					button.setButtonCheckFlag(true);
			}
		}
		// resource.setButton(resButtons);
		return resource;
	}


	public void addResourceToRole(String roleId, List<Resource> resources) {
		RoleResource roleResource = roleResourceDao
				.getRoleResourceByRoleId(roleId);
		LinkedHashSet<Resource> dbResources = new LinkedHashSet<Resource>();
		if (roleResource == null) {
			roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
		}
		if (CollectionUtil.isEmpty(resources)) {
			roleResource.setResources(null);
		} else {
			for (Resource resource : resources) {

				if (resource.getResourceFlag() != null
						&& resource.getResourceFlag()) {
					Resource dbResource = resourceDao.getResourceById(resource
							.getId());
					List<Button> buttons = getButtonByIds(resource.getButton());
					dbResource.setButton(buttons);
					dbResources.add(dbResource);
				}
			}
		}
		roleResource.setResources(dbResources);
		roleResource.setRefResources(dbResources);
		roleResourceDao.saveRoleResource(roleResource);
	}

	private List<Button> getButtonByIds(List<Button> buttons) {
		if (CollectionUtil.isEmpty(buttons))
			return null;
		for (Button button : buttons) {
			button = buttonDao.getButtonById(button.getId());
		}
		return buttons;
	}
}
