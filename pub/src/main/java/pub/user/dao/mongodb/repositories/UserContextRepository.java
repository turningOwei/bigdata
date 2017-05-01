package pub.user.dao.mongodb.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import pub.user.domain.UserContext;

public interface UserContextRepository extends
		MongoRepository<UserContext, Long> {

}
