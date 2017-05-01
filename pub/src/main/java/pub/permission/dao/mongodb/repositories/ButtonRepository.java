package pub.permission.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.permission.domain.Button;


public interface ButtonRepository extends MongoRepository<Button, String> {

}
