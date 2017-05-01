package datacenterbizapiexternal.datapush.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;

@SuppressWarnings("rawtypes")
public interface TourTraceDistributionRepository extends
		MongoRepository<TourTraceDistributionEntity, String> {

}
