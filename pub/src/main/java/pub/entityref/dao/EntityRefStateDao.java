package pub.entityref.dao;

import pub.entityref.domain.EntityInfo;
import pub.entityref.domain.EntityRefState;

public interface EntityRefStateDao {

	EntityRefState getEntityRefStateByEntityInfo(EntityInfo info);

	void save(EntityRefState state) throws Throwable;

	void updateRefCount(EntityRefState state);

}
