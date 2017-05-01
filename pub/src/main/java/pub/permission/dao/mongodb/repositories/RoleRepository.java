package pub.permission.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.permission.domain.Role;


public interface RoleRepository extends MongoRepository<Role, String> {

}