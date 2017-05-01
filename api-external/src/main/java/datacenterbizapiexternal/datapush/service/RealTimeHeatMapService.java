package datacenterbizapiexternal.datapush.service;

import java.util.Date;

import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMapEntity;

public interface RealTimeHeatMapService {
	public void addEntity(RealTimeHeatMapEntity<RealTimeHeatMap> entity);

	/**
	 * @Title: queryEntityByStartTime
	 * @Description: 用于api的查询,当前半个小时可能需要直接从接口中查询(这个时候可能没有同步到db)
	 * @param @param startTime 格式为yyyy-MM-dd HH:mm
	 * @param @return 设定文件
	 * @return RealTimeHeatMapEntity 返回类型
	 */
	public RealTimeHeatMapEntity<RealTimeHeatMap> queryEntityByStartTime(
			Date startTime);

	/**
	 * @Title: queryEntityByStartTimeThroughDB
	 * @Description: 只是从数据库中查询
	 * @param @param startTime
	 * @param @return 设定文件
	 * @return RealTimeHeatMapEntity 返回类型
	 */
	public RealTimeHeatMapEntity<RealTimeHeatMap> queryEntityByStartTimeThroughDB(
			Date startTime);
}
