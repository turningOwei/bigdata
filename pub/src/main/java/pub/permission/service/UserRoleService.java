package pub.permission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.GrantRoleResult;
import pub.permission.domain.Role;
import pub.permission.domain.UserRole;


/**
 * 
 * @ClassName: UserRoleService
 * @Description: 用户关联角色表
 * @author weipeng 175408322@qq.com
 * @date 2016年1月20日 下午7:35:00
 *
 */
public interface UserRoleService {

	public Page<Role> getUserRoleByUserId(String id,
										  PageRequest buildPageRequest);

	public GrantRoleResult grantRoleToUser(String[] ids, String[] noncheckIds,
										   String userId);

	public UserRole getUserRoleByUserId(String userId);
}
