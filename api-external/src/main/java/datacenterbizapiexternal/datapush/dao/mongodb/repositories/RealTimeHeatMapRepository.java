package datacenterbizapiexternal.datapush.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMapEntity;

public interface RealTimeHeatMapRepository extends
		MongoRepository<RealTimeHeatMapEntity<RealTimeHeatMap>, String> {
	public RealTimeHeatMapEntity<RealTimeHeatMap> findOneByStartTime(String time);
}
