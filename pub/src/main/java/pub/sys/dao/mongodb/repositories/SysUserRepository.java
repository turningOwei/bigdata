package pub.sys.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.sys.domain.SysUser;


public interface SysUserRepository extends MongoRepository<SysUser, String> {

}
