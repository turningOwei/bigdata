package pub.user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.user.domain.Account;
import pub.user.domain.LoginResult;
import pub.user.domain.User;


/**
 * @author zhaowei
 *
 */
public interface UserService {

	/**
	 * 登录
	 * 
	 * @param account
	 * @return 代表登录结果的对象
	 */
	LoginResult login(Account account);

	/**
	 * 判断登录名是否唯一 true为唯一，false为非唯一
	 */
	boolean isUnique(String loginName);

	/**
	 * @param user
	 * @return true添加成功，false账户重复
	 * @throws Throwable
	 */
	boolean addUser(User user) throws Throwable;

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	User getUserById(String id) throws Throwable;

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return boolean true成功，false账户重复
	 * @throws Throwable
	 */
	boolean updateUser(User user) throws Throwable;

	/**
	 * 删除用户
	 */
	int deleteUser(User user) throws Throwable;

	public Page<User> getUsers(PageRequest pageRequest);

	public Page<User> getUsersByNameLike(String name,
										 PageRequest buildPageRequest);

	public List<User> getUserByName(String name);

	public User getUserByLoginName(String loginName);

}
