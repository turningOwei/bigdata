package pub.permission.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.permission.domain.UserRole;


public interface UserRoleRepository extends MongoRepository<UserRole, String> {

	public UserRole findOneByUserId(String userId);

}
