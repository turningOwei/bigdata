package pub.permission.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import framework.util.CollectionUtil;
import framework.util.StringUtil;
import pub.permission.dao.ButtonDao;
import pub.permission.dao.ResourceDao;
import pub.permission.dao.RoleResourceDao;
import pub.permission.dao.UserRoleDao;
import pub.permission.domain.Button;
import pub.permission.domain.Resource;
import pub.permission.domain.Role;
import pub.permission.domain.RoleResource;
import pub.permission.domain.UserRole;
import pub.permission.service.ResourceService;
import pub.sys.dao.SysCodeItemDao;
import pub.user.domain.User;

/**
 * 资源service层接口实现
 * 
 * @author wang xp
 * @date 2015-06-02
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private SysCodeItemDao sysCodeItemDao;
	@Autowired
	private ButtonDao buttonDao;

	/**
	 * 获取资源列表
	 * 
	 * @param start,size
	 * 
	 */

	public List<Resource> getResourceList(int start, int size) {
		List<Resource> resourceList = resourceDao.getResourceList(start, size);
		return resourceList;
	}

	/**
	 * 获取资源总数
	 * 
	 */

	public Integer getTotalResource() {
		Integer count = resourceDao.getTotalResource();
		return count;
	}

	/**
	 * 根据id获取资源
	 */

	public Resource getResourceById(Long id) {
		Resource res = resourceDao.getResourceById(id);
		return res;
	}

	/**
	 * 根据id获取Resource集合信息
	 */

	public List<Resource> getResourceByIdList(Long[] resourceIds) {
		List<Resource> res = resourceDao.getResourceByIdList(resourceIds);
		return res;
	}

	/**
	 * 根据用户id获取用户资源
	 */

	public List<Resource> getResourceByUserId(String userId) {
		List<UserRole> userRoles = userRoleDao.getUserRolesByUserId(userId);
		List<String> roleIds = getRoleIds(userRoles);
		List<RoleResource> roleResources = roleResourceDao
				.getRoleResourcesByRoleIds(roleIds);
		List<Resource> list = getDistinctResource(roleResources);
		return list;
	}

	/**
	 * 修改用户资源
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean updateResource(Resource resource) throws Throwable {
		Integer n = resourceDao.updateResource(resource);
		return n > 0 ? true : false;
	}

	/**
	 * 删除用户资源
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean deleteResource(Resource resource) throws Throwable {
		Integer n = resourceDao.deleteResource(resource);
		return n > 0 ? true : false;
	}

	/**
	 * 新增用户资源
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean addResource(Resource resource) throws Throwable {
		Integer n = resourceDao.addResource(resource);
		return n > 0 ? true : false;
	}


	public Resource getResourceByResourceStr(String str) {
		return resourceDao.getResourceByResourceStr(str);
	}


	public Resource getResourceByName(String name) {
		return resourceDao.getResourceByName(name);
	}


	public List<Resource> getAllResources() {
		return resourceDao.getAllResources();
	}


	public Page<Resource> getResources(PageRequest buildPageRequest) {
		return resourceDao.getResources(buildPageRequest);
	}


	public Resource getResourceById(String id) {
		return resourceDao.getResourceById(id);
	}


	public List<Resource> listResourcesByUserId(String userId) {
		List<UserRole> userRoles = userRoleDao.getUserRolesByUserId(userId);
		List<String> roleIds = getRoleIds(userRoles);
		List<RoleResource> roleResources = roleResourceDao
				.getRoleResourcesByRoleIds(roleIds);
		List<Resource> resources = getDistinctResource(roleResources);
		return resources;
	}


	public List<String> getResourceStr(String userId) {
		List<Resource> resources = this.listResourcesByUserId(userId);
		List<String> resourceStrs = new ArrayList<String>();
		if (CollectionUtil.isNotEmpty(resources)) {
			for (Resource resource : resources) {
				resourceStrs.add(resource.getResourceStr().trim());
				List<String> buttonResourceStrs = getButtonResourceStr(resource
						.getButton());
				if (CollectionUtil.isNotEmpty(buttonResourceStrs))
					resourceStrs.addAll(buttonResourceStrs);
			}
		}
		return resourceStrs;
	}

	private List<String> getButtonResourceStr(List<Button> buttons) {
		List<String> buttonResourceStr = new ArrayList<String>();
		if (CollectionUtil.isNotEmpty(buttons)) {
			for (Button button : buttons) {
				if (button != null
						&& !StringUtil.isEmpty(button.getResourceStr())) {
					buttonResourceStr.add(button.getResourceStr());
				}
			}
			return buttonResourceStr;
		}
		return null;
	}

	private List<Resource> getDistinctResource(List<RoleResource> roleResources) {
		if (CollectionUtil.isNotEmpty(roleResources)) {
			Map<String, Resource> map = new LinkedHashMap<String, Resource>();
			for (RoleResource roleResource : roleResources) {
				LinkedHashSet<Resource> innerResoureces = roleResource
						.getRefResources();
				if (CollectionUtil.isNotEmpty(innerResoureces)) {
					for (Resource innerResourece : innerResoureces) {
						if (innerResourece != null)
							map.put(innerResourece.getId(), innerResourece);
					}
				}
			}
			return CollectionUtil.mapToArrayList(map);
		}
		return null;
	}

	private List<String> getRoleIds(List<UserRole> userRoles) {
		if (CollectionUtil.isNotEmpty(userRoles)) {
			List<String> list = new ArrayList<String>();
			for (UserRole userRole : userRoles) {
				LinkedHashSet<Role> roles = userRole.getRoles();
				if (CollectionUtil.isNotEmpty(roles)) {
					Iterator<Role> it = roles.iterator();
					while (it.hasNext()) {
						Role role = it.next();
						list.add(role.getId());
					}
				}
			}
			return list;
		}
		return null;
	}


	public List<Resource> getResourceTree(User user) {
		List<Resource> allLst;
		if (user != null && !StringUtils.isEmpty(user.getId())) {
			allLst = this.getResourceByUserId(user.getId());
		} else {
			allLst = resourceDao.getAllResources();
		}
		List<Resource> rootNodes = this.getRootNodes(allLst);
		if (CollectionUtil.isEmpty(rootNodes))
			return null;
		for (Resource resource : rootNodes) {
			List<Resource> childrens = this.getChildrenNodes(resource, allLst);
			if (CollectionUtil.isNotEmpty(childrens))
				resource.setChildren(childrens);
		}
		return rootNodes;
	}

	private List<Resource> getRootNodes(List<Resource> allLst) {
		List<Resource> parentLst = new ArrayList<Resource>();
		if (CollectionUtil.isNotEmpty(allLst)) {
			for (Resource resource : allLst) {
				if (resource.getSupResource() == null) {
					parentLst.add(resource);
				}
			}
		}
		return parentLst;
	}

	private List<Resource> getChildrenNodes(Resource parent,
			List<Resource> allLst) {
		List<Resource> childrenNodes = new ArrayList<Resource>();
		for (Resource resource : allLst) {
			if (resource.getSupResource() != null
					&& (resource.getSupResource().getId()
							.equals(parent.getId()))) {
				List<Resource> grandchilds = getChildrenNodes(resource, allLst);
				if (CollectionUtil.isNotEmpty(grandchilds))
					resource.setChildren(grandchilds);
				childrenNodes.add(resource);
			}
		}
		return childrenNodes;
	}


	public Resource getResourceButtonById(String id) {
		Resource dbResource = resourceDao.getResourceById(id);
		/*
		 * List<SysCodeItem> buttonCodeItems = sysCodeItemDao
		 * .findByCodeType(SysCode.BUTTON);
		 */
		/*
		 * List<Button> buttons = dbResource.getButton(); int size =
		 * CollectionUtil.isEmpty(buttonCodeItems) ? 0 : buttonCodeItems.size();
		 * List<Button> outButtons = new ArrayList<Button>(); if
		 * (CollectionUtil.isEmpty(buttons)) { for (int i = 0; i < size; i++) {
		 * SysCodeItem buttonCodeItem = buttonCodeItems.get(i); Button button =
		 * new Button(buttonCodeItem.getName(), "", ""); outButtons.add(button);
		 * } } else { for (int i = 0; i < size; i++) { SysCodeItem
		 * buttonCodeItem = buttonCodeItems.get(i); boolean hasButton = false;
		 * for (Button button : buttons) { if (button != null &&
		 * buttonCodeItem.getName() .equals(button.getName())) hasButton = true;
		 * } if (!hasButton) { Button button = new
		 * Button(buttonCodeItem.getName(), "", ""); outButtons.add(button); } }
		 * outButtons.addAll(buttons); } dbResource.setButton(outButtons);
		 */
		return dbResource;
	}


	public void resourceAddButton(String resourceId, List<Button> buttons)
			throws Throwable {
		if (CollectionUtil.isNotEmpty(buttons)) {
			for (Button button : buttons) {
				if (StringUtils.isEmpty(button.getId()))
					button.setId(null);
				button.setResourceId(resourceId);
			}
			buttonDao.batchSave(buttons);
			Resource resource = resourceDao.getResourceById(resourceId);
			resource.setButton(buttons);
			resourceDao.updateResource(resource);
		}
	}


	public List<Resource> queryResourceByUserId(String userId) {
		List<UserRole> userRoles = userRoleDao.getUserRolesByUserId(userId);
		List<String> roleIds = getRoleIds(userRoles);
		List<RoleResource> roleResources = roleResourceDao
				.getRoleResourcesByRoleIds(roleIds);
		List<Resource> list = getResource(roleResources);

		List<Resource> result = new ArrayList<Resource>();
		for (Resource res : list) {
			if (res.getSupResource() == null) {
				result.add(res);
			}

		}
		for (Resource superRes : result) {
			for (Resource res : list) {
				if (res.getSupResource() != null) {
					if (superRes.getId().equals(res.getSupResource().getId())) {
						List<Resource> children = new ArrayList<Resource>();
						if (superRes.getChildren() != null) {
							children = superRes.getChildren();
						}
						children.add(res);
						if (children != null) {
							for (Resource child : children) {
								child.setSupResource(null);
								child.setId(null);
								child.setMemo(null);
								child.setIs_valid(null);
								if (child.getResourceStr() != null) {
									child.setUrl(child.getResourceStr());
								}
								child.setResourceStr(null);
							}
						}
						sortByDisOrder(children);
						superRes.setChildren(children);
					}
				}
			}
		}
		sortByDisOrder(result);
		return result;
	}

	private void sortByDisOrder(List<Resource> children){
		Collections.sort(children, new Comparator<Resource>() {

			public int compare(Resource o1, Resource o2) {
				return o1.getDisOrder()>o2.getDisOrder()?1:-1;
			}
		});
	}
	
	private List<Resource> getResource(List<RoleResource> roleResources) {
		if (CollectionUtil.isNotEmpty(roleResources)) {
			Map<String, Resource> map = new LinkedHashMap<String, Resource>();
			for (RoleResource roleResource : roleResources) {
				LinkedHashSet<Resource> innerResoureces = roleResource
						.getRefResources();
				if (CollectionUtil.isNotEmpty(innerResoureces)) {
					for (Resource innerResourece : innerResoureces) {
						if (innerResourece != null
								&& innerResourece.getIs_valid().equals("0"))
							map.put(innerResourece.getId().toString(),
									innerResourece);
					}
				}
			}
			return CollectionUtil.mapToArrayList(map);
		}
		return null;

	}
}
