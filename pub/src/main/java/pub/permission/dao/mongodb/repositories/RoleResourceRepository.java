package pub.permission.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.permission.domain.RoleResource;


public interface RoleResourceRepository extends
		MongoRepository<RoleResource, String> {

	public RoleResource findOneByRoleId(String roleId);

}
