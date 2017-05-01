package pub.sys.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pub.permission.domain.SysCodeItem;
import pub.sys.dao.SysCodeItemDao;
import pub.sys.dao.mongodb.repositories.SysCodeItemRepository;

@Repository
public class MongoSysCodeItemDao implements SysCodeItemDao {
	@Autowired
	private SysCodeItemRepository repository;


	public Page<SysCodeItem> querySysCodeItem(SysCodeItem sysCodeItem,
			PageRequest buildPageRequest) {
		return repository.findAll(buildPageRequest);
	}


	public void saveOrUpdate(SysCodeItem entity) {
		repository.save(entity);
	}


	public void batchSave(List<SysCodeItem> itemList) {
		repository.save(itemList);
	}


	public List<SysCodeItem> findByCodeType(String codeType) {
		return repository.findByCodeType(codeType);
	}


	public SysCodeItem querySysCodeItemByCodeType(String codeType) {
		return repository.findOneByCodeType(codeType);
	}
}
