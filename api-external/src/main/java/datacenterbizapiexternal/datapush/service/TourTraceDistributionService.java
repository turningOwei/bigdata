package datacenterbizapiexternal.datapush.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistribution;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;

public interface TourTraceDistributionService {

	public void addEntity(
			TourTraceDistributionEntity<TourTraceDistribution> tourTraceDistribution);

	public List<TourTraceDistributionEntity<TourTraceDistribution>> queryEntityByStartTimeAndEndTime(
			String startTime, String endTime);

	public File getTraceExcel(String title, List<Map> list) throws IOException;
}
