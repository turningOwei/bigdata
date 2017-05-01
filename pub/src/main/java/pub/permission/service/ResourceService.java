package pub.permission.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.Button;
import pub.permission.domain.Resource;
import pub.user.domain.User;


/**
 * 资源service层接口
 * 
 * @author wang xp
 * @date 2015-06-02
 */
public interface ResourceService {

	/**
	 * 获取资源列表
	 * 
	 */
	List<Resource> getResourceList(int start, int size);

	/**
	 * 获取资源总数
	 */
	Integer getTotalResource();

	/**
	 * 获取所有资源
	 * 
	 * @return
	 */
	List<Resource> getAllResources();

	/**
	 * 根据id获取资源
	 * 
	 * @param id
	 * @return resource
	 */
	Resource getResourceById(Long id);

	public Resource getResourceById(String id);

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
	public boolean updateResource(Resource resource) throws Throwable;

	/**
	 * 删除用户资源
	 */
	public boolean deleteResource(Resource resource) throws Throwable;

	/**
	 * 新增用户资源
	 */
	public boolean addResource(Resource resource) throws Throwable;

	Resource getResourceByResourceStr(String str);

	public Page<Resource> getResources(PageRequest buildPageRequest);

	public Resource getResourceByName(String name);

	public List<Resource> listResourcesByUserId(String userId);

	public List<String> getResourceStr(String userId);

	List<Resource> getResourceTree(User user);

	Resource getResourceButtonById(String id);

	public void resourceAddButton(String resourceId, List<Button> buttons)
			throws Throwable;

	/**
	 * 根据用户id获取用户资源
	 * 
	 * @param userId
	 * @return List
	 */
	List<Resource> queryResourceByUserId(String userId);

}
