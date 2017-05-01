package pub.user.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import pub.user.dao.UserContextDao;
import pub.user.dao.mongodb.repositories.UserContextRepository;
import pub.user.domain.UserContext;


public class MongodbUserContextDao implements UserContextDao {

	@Autowired
	private UserContextRepository repository;


	public void save(UserContext userContext) throws Throwable {
		repository.save(userContext);
	}

}
