package pub.user.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.user.dao.UserDao;
import pub.user.dao.mongodb.repositories.UserRepository;
import pub.user.domain.User;


public class MongodbUserDao implements UserDao {

	@Autowired
	private UserRepository repository;


	public void saveUser(User user) throws Throwable {
		repository.save(user);
	}


	public User getUserById(String id) throws Throwable {
		return repository.findOne(id);
	}


	public User getUserByLoginName(String loginName) {
		return repository.findOneByAccountLoginName(loginName);
	}


	public int updateUser(User user) throws Throwable {
		repository.save(user);
		return 1;
	}


	public int deleteUser(User user) throws Throwable {
		repository.delete(user);
		return 1;
	}


	public Page<User> getUsers(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}


	public Page<User> getUsersByNameLike(String name,
			PageRequest buildPageRequest) {
		return repository.findUserByNameLike(name, buildPageRequest);
	}


	public List<User> getUserByName(String name) {
		return repository.getUserByName(name);
	}

}
