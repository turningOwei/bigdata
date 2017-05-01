package pub.permission.service.impl;

import pub.permission.domain.Resource;
import pub.permission.domain.ResourceAble;
import pub.permission.service.PermissionHandleService;
import pub.permission.service.ResourceService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * 资源service层接口实现
 * 
 * @author wang xp
 * @date 2015-06-02
 */
public class PermissionHandleServiceImpl implements PermissionHandleService {

	@javax.annotation.Resource(name = "resourceService")
	private ResourceService resourceService;

	/**
	 * 根据参数userId和resourceStr判断用户是否有该资源操作权限
	 * 
	 * @return boolean true有权限，false反之
	 */

	public boolean isHasPermission(String userId, String resourceStr) {
		List<Resource> resList = resourceService.getResourceByUserId(userId);
		boolean flag = false;
		for (Resource res : resList) {
			String resStr = res.getResourceStr();
			if (resourceStr.equals(resStr)) {
				flag = true;
				break;
			}
		}
		return flag;
	}


	public void filterResourcesByPermission(String userId,
			List<ResourceAble> resources) {
		List<Resource> resList = resourceService.getResourceByUserId(userId);
		Set<String> resStrSet = new HashSet<String>();
		for (Resource resource : resList) {
			resStrSet.add(resource.getResourceStr());
		}
		Iterator<ResourceAble> i = resources.iterator();
		while (i.hasNext()) {
			ResourceAble ra = i.next();
			if (!resStrSet.contains(ra.getResourceStr())) {
				i.remove();
			}
		}
	}

}
