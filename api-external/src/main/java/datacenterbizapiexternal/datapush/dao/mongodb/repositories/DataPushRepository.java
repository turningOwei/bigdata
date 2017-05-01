package datacenterbizapiexternal.datapush.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;

@SuppressWarnings("rawtypes")
public interface DataPushRepository extends
		MongoRepository<DataPushEntity, String> {
	public DataPushEntity findOneByTimeAndType(String time, String type);

	
}
