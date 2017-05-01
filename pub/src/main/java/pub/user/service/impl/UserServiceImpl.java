package pub.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.user.dao.AccountDao;
import pub.user.dao.UserDao;
import pub.user.domain.Account;
import pub.user.domain.LoginResult;
import pub.user.domain.User;
import pub.user.service.UserService;


/**
 * @author zhaowei
 * @date 2016-01-25
 */
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccountDao accountDao;

	/**
	 * @param user
	 * @return true添加成功，false账户重复
	 * @throws Throwable
	 */

	public boolean addUser(User user) throws Throwable {
		// 账户是否已存在
		if (this.isUnique(user.getAccount().getLoginName())) {
			user.getAccount().encryptPass();
			userDao.saveUser(user);
			return true;
		} else {
			return false;
		}
	}


	public User getUserById(String id) throws Throwable {
		return userDao.getUserById(id);
	}


	public LoginResult login(Account account) {
		LoginResult result = new LoginResult();
		Account existsAccount = accountDao.getAccountByLoginName(account
				.getLoginName());
		if (existsAccount != null) {
			if (existsAccount.isPassWordSame(account)) {
				return result;
			} else {
				result.unSuccessByWrongPass();
				return result;
			}
		} else {
			result.unSuccessByNoAccount();
			return result;
		}
	}

	/**
	 * 判断登录名是否唯一 true为唯一，false为非唯一
	 */

	public boolean isUnique(String loginName) {
		Account account = accountDao.getAccountByLoginName(loginName);
		if (account != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 修改用户信息 true成功，false账户重复
	 */

	public boolean updateUser(User user) throws Throwable {
		String fromLoginName = user.getAccount().getLoginName();
		User userTmp = userDao.getUserById(user.getId());
		if (userTmp == null) {
			throw new Exception("账户被意外删除！");
		}

		if (fromLoginName.equals(userTmp.getAccount().getLoginName())) {
			if (!user.getAccount().getPassWord()
					.equals(userTmp.getAccount().getPassWord()))
				user.getAccount().encryptPass();
			return userDao.updateUser(user) > 0;
		} else {
			// 账户是否已存在
			if (this.isUnique(user.getAccount().getLoginName())) {
				return userDao.updateUser(user) > 0;
			} else {
				return false;
			}
		}
	}

	/**
	 * 删除用户
	 */

	public int deleteUser(User user) throws Throwable {
		int n = userDao.deleteUser(user);
		return n;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	public Page<User> getUsers(PageRequest pageRequest) {
		return userDao.getUsers(pageRequest);
	}


	public Page<User> getUsersByNameLike(String name,
			PageRequest buildPageRequest) {
		return userDao.getUsersByNameLike(name, buildPageRequest);
	}


	public List<User> getUserByName(String name) {
		return userDao.getUserByName(name);
	}


	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}
}
