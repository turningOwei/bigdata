package pub.user.dao.mybatis;

import java.util.List;

import framework.orm.mybatis.MybatisDaoBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.user.dao.UserDao;
import pub.user.domain.User;


public class MybatisUserDao extends MybatisDaoBase<User> implements UserDao {


	public void saveUser(User user) throws Throwable {
		openSession().insert("user.saveUser", user);
	}


	public User getUserById(String id) throws Throwable {
		return openSession().selectOne("user.getUserById", id);
	}


	public User getUserByLoginName(String loginName) {
		User user = this.sqlSession.selectOne("user.getUserByLoginName",
				loginName);
		return user;
	}

	/**
	 * 修改用户信息
	 */

	public int updateUser(User user) throws Throwable {
		int n = this.sqlSession.update("user.updateUser", user);
		return n;
	}

	/**
	 * 删除用户
	 */

	public int deleteUser(User user) throws Throwable {
		int n = this.sqlSession.delete("user.deleteUser", user);
		return n;
	}


	public Page<User> getUsers(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<User> getUsersByNameLike(String name,
			PageRequest buildPageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<User> getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
