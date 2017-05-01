package datacenterbizapiexternal.datapush.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeTouristNumber;

public interface DataPushDistributionService<T> {
	public void addDataPushEntity(DataPushEntity<T> entity);

	public void addDataPushEntities(List<DataPushEntity<T>> entities);

	@SuppressWarnings("rawtypes")
	public DataPushEntity queryEntityByTimeAndType(String time, String type);

	@SuppressWarnings("rawtypes")
	public List<DataPushEntity> touristTotalDistrubition(String startTime,
														 String endTime);

	public RealTimeTouristNumber realTimeTouristNumber();

	public File getExcel(List<Map<String, String>> consume,
						 List<Map<String, String>> age, List<Map<String, String>> gender,
						 String title, String date);

}
