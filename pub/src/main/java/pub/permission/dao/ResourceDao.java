package pub.permission.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pub.permission.domain.Resource;


/**
 * 资源dao层操作
 * 
 * @author wang xp
 * @date 2015-05-28
 */
public interface ResourceDao {

	/**
	 * 获取资源列表
	 */
	List<Resource> getResourceList(int start, int size);

	/**
	 * 获取资源总数
	 */
	Integer getTotalResource();

	/**
	 * 根据id获取资源
	 * 
	 * @param id
	 * @return resource
	 */
	Resource getResourceById(Long id);

	/**
	 * 根据id获取Resource集合信息
	 */
	public List<Resource> getResourceByIdList(Long[] resourceIds);

	/**
	 * 根据用户id获取用户资源
	 * 
	 * @param userId
	 * @return List
	 */
	List<Resource> getResourceByUserId(String userId);

	/**
	 * 修改用户资源
	 */
	Integer updateResource(Resource resource) throws Throwable;

	/**
	 * 删除用户资源
	 */
	Integer deleteResource(Resource resource) throws Throwable;

	/**
	 * 添加用户资源
	 */
	Integer addResource(Resource resource) throws Throwable;

	Resource getResourceByResourceStr(String str);

	List<Resource> getAllResources();

	public Page<Resource> getResources(PageRequest buildPageRequest);

	public Resource getResourceByName(String name);

	public Resource getResourceById(String id);

	public List<Resource> getResourceByIds(List<String> ids);

	List<Resource> getAllResourcesSort(Sort sort);

}
