package pub.entityref.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import pub.entityref.domain.EntityRefState;

public interface EntityRefStateRepository extends
		MongoRepository<EntityRefState, String> {
	EntityRefState findOneByEntityInfoEntityTypeAndEntityInfoEntityId(
			String entityType, String entityTyId);
}