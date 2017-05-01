package pub.permission.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.AddResourceResult;
import pub.permission.domain.Resource;


/**
 * 
 * @ClassName: RoleResourceService
 * @Description: 角色对应资源(菜单)
 * @author weipeng 175408322@qq.com
 * @date 2016年1月22日 上午11:36:12
 *
 */
public interface RoleResourceService {

	public Page<Resource> getResourcesByRoleId(String id,
											   PageRequest buildPageRequest);

	public Page<Resource> getResourcesByNameLike(String name,
												 PageRequest buildPageRequest);

	public AddResourceResult addResourceToRole(String[] ids,
											   String[] noncheckIds, String roleId);

	public List<Resource> getResourcesByRoleId(String roleId);

	public void addResourceToRole(String roleId, List<Resource> resources);

}
