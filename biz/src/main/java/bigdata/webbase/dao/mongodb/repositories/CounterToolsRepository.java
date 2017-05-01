package bigdata.webbase.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import bigdata.webbase.domain.CounterTools;

public interface CounterToolsRepository extends
		MongoRepository<CounterTools, String> {

}
