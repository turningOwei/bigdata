package pub.entityref.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;

import framework.orm.mongodb.MongodbDaoBase;
import org.springframework.stereotype.Repository;
import pub.entityref.dao.EntityRefStateDao;
import pub.entityref.dao.mongodb.repositories.EntityRefStateRepository;
import pub.entityref.domain.EntityInfo;
import pub.entityref.domain.EntityRefState;
@Repository
public class MongodbEntityRefStateDao extends MongodbDaoBase<EntityRefState>
		implements EntityRefStateDao {

	@Autowired
	private EntityRefStateRepository repository;


	protected Class<EntityRefState> getEntityClass() {
		return EntityRefState.class;
	}


	public EntityRefState getEntityRefStateByEntityInfo(EntityInfo info) {
		return repository.findOneByEntityInfoEntityTypeAndEntityInfoEntityId(
				info.getEntityType(), info.getEntityId());
	}


	public void save(EntityRefState state) throws Throwable {
		repository.save(state);
	}


	public void updateRefCount(EntityRefState state) {
		repository.save(state);
	}

}
