package pub.permission.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.permission.domain.Resource;


public interface ResourceRepository extends MongoRepository<Resource, String> {

	public Resource findOneByName(String name);

}
