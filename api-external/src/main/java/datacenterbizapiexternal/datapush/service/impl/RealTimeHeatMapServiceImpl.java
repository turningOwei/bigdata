package datacenterbizapiexternal.datapush.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import datacenterbizapiexternal.datapush.DataPushClient;
import datacenterbizapiexternal.datapush.dao.mongodb.repositories.RealTimeHeatMapRepository;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMapEntity;
import datacenterbizapiexternal.datapush.service.RealTimeHeatMapService;
import framework.util.date.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class RealTimeHeatMapServiceImpl implements RealTimeHeatMapService {
	@Autowired
	private RealTimeHeatMapRepository repository;

	public void addEntity(RealTimeHeatMapEntity<RealTimeHeatMap> entity) {
		repository.insert(entity);
	}

	/**
	 * @params String time 格式为yyyy-MM-dd HH:mm
	 */
	public RealTimeHeatMapEntity<RealTimeHeatMap> queryEntityByStartTime(
			Date startTime) {
		String startTimeStr = DateUtil.getRealTimeHeatMapHttpStart(startTime);
		String nowStart = DateUtil.getRealTimeHeatMapHttpStart(new Date());
		// 如果时间是现在的开始时间
		if (nowStart.equals(startTimeStr)) {
			return DataPushClient.getRealTimeHeatMap("");
		}
		return repository.findOneByStartTime(startTimeStr);
	}

	public RealTimeHeatMapEntity<RealTimeHeatMap> queryEntityByStartTimeThroughDB(
			Date startTime) {
		String startTimeStr = DateUtil.getRealTimeHeatMapHttpStart(startTime);
		return repository.findOneByStartTime(startTimeStr);
	}
}
