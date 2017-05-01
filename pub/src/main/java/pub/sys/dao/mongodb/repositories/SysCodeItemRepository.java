package pub.sys.dao.mongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import pub.permission.domain.SysCodeItem;


public interface SysCodeItemRepository extends
		MongoRepository<SysCodeItem, String> {
	public List<SysCodeItem> findByCodeType(String codeType);

	public SysCodeItem findOneByCodeType(String codeType);
}
