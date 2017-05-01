package pub.sys.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.sys.domain.SysEnums;


public interface SysEnumsRepository extends MongoRepository<SysEnums, String> {

}
