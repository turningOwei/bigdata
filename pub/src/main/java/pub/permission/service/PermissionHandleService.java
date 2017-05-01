package pub.permission.service;

import pub.permission.domain.ResourceAble;

import java.util.List;


/**
 * 权限处理service层接口
 * 
 * @author wang xp
 * @date 2015-06-04
 */
public interface PermissionHandleService {

	/**
	 * 根据参数userId和resourceStr判断用户是否有该资源操作权限
	 * 
	 * @return boolean true有权限，false反之
	 */
	boolean isHasPermission(String userId, String resourceStr);

	/**
	 * 根据用户用拥有的权限来过滤资源
	 * 
	 * @param userId
	 * @param resources
	 */
	void filterResourcesByPermission(String userId, List<ResourceAble> resources);

}
