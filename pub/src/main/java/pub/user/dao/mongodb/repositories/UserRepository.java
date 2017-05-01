package pub.user.dao.mongodb.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import pub.user.domain.User;


public interface UserRepository extends MongoRepository<User, String> {
	User findOneByAccountLoginName(String loginName);

	public Page<User> findUserByNameLike(String name, Pageable pageable);

	public List<User> getUserByName(String name);
}