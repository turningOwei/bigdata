package pub.user.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.user.domain.User;


public interface UserDao {

	void saveUser(User user) throws Throwable;

	User getUserById(String id) throws Throwable;

	User getUserByLoginName(String loginName);

	/**
	 * 修改用户信息
	 */
	int updateUser(User user) throws Throwable;

	/**
	 * 删除用户
	 */
	int deleteUser(User user) throws Throwable;

	public Page<User> getUsers(PageRequest pageRequest);

	public Page<User> getUsersByNameLike(String name,
										 PageRequest buildPageRequest);

	public List<User> getUserByName(String name);

}
