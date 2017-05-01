package datacenterbizapiexternal.datapush;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.DataPushResponseParams;
import datacenterbizapiexternal.datapush.domain.HttpStatus;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMapEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeTouristNumber;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistribution;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;
import framework.util.CollectionUtil;
import framework.util.StringUtil;
import framework.util.date.DateUtil;
import pub.http.HttpObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @ClassName: DataPushClient
 * @Description: 个推数据客户端
 * @author weipeng 175408322@qq.com
 * @date 2016年2月29日 上午9:32:19
 *
 */
public class DataPushClient {
	public static DataPushEntity<DataPushDistribution> getDataPushObj(
			String time, DataPushExternalEnum dataPushEnum) {
		String url = dataPushEnum.getUrl() + "/" + time;
		String json = HttpObject.getJsonByGet(url);

		DataPushResponseParams<DataPushDistribution> result = DataPushClient
				.getDataPushDistributionResponseObj(json);
		if (result == null || result.getStatus() == null
				|| !result.getStatus().equals(HttpStatus.OK.getValue()))
			return null;
		List<DataPushDistribution> list = result.getData();
		if (CollectionUtil.isEmpty(list))
			return null;
		DataPushEntity<DataPushDistribution> entity = new DataPushEntity<DataPushDistribution>();
		entity.setType(dataPushEnum.getType());
		entity.setTime(time);
		entity.setList(list);
		entity.setUrl(url);
		entity.setDescription(dataPushEnum.getDescription());
		return entity;
	}

	public static RealTimeHeatMapEntity<RealTimeHeatMap> getRealTimeHeatMapObj(
			String time, DataPushExternalEnum dataPushEnum) {
		String url = dataPushEnum.getUrl() + "/" + time;
		Date httpStart = new Date();
		String json = HttpObject.getJsonByGet(url);

		DataPushResponseParams<RealTimeHeatMap> result = DataPushClient
				.getRealTimeHeatMapResponseObj(json);
		if (!result.getStatus().equals(HttpStatus.OK.getValue()))
			return null;
		List<RealTimeHeatMap> list = result.getData();
		if (CollectionUtil.isEmpty(list))
			return null;
		RealTimeHeatMapEntity<RealTimeHeatMap> entity = new RealTimeHeatMapEntity<RealTimeHeatMap>();
		entity.setType(dataPushEnum.getType());
		entity.setTime(time);
		entity.setList(list);
		entity.setUrl(url);
		entity.setStartTime(DateUtil.getRealTimeHeatMapHttpStart(httpStart));
		entity.setEndTime(DateUtil.getRealTimeHeatMapHttpEnd(httpStart));
		return entity;
	}

	public static TourTraceDistributionEntity<TourTraceDistribution> getTourTraceObj(
			String time, DataPushExternalEnum dataPushEnum) {
		String url = dataPushEnum.getUrl() + "/" + time;
		String json = HttpObject.getJsonByGet(url);

		DataPushResponseParams<TourTraceDistribution> result = DataPushClient
				.getTourTraceDistributionResponseObj(json);
		if (!result.getStatus().equals(HttpStatus.OK.getValue()))
			return null;
		List<TourTraceDistribution> list = result.getData();
		if (CollectionUtil.isEmpty(list))
			return null;
		TourTraceDistributionEntity<TourTraceDistribution> entity = new TourTraceDistributionEntity<TourTraceDistribution>();
		entity.setType(dataPushEnum.getType());
		entity.setTime(time);
		entity.setList(list);
		entity.setUrl(url);
		return entity;
	}

	/**
	 * 
	 * @Title: getResponseObj
	 * @Description: 根据响应后的json转换成所需response对象
	 * @param @param json
	 * @param @return response对象
	 * @return BaiduWeatherResponseParams 返回类型
	 */
	public static DataPushResponseParams<DataPushDistribution> getDataPushDistributionResponseObj(
			String json) {
		DataPushResponseParams<DataPushDistribution> result = new DataPushResponseParams<DataPushDistribution>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// List<DataPushDistribution> list = new
		// ArrayList<DataPushDistribution>();
		if (!StringUtil.isEmpty(json))
			result = gson
					.fromJson(
							json,
							new TypeToken<DataPushResponseParams<DataPushDistribution>>() {
							}.getType());
		// result.setData(list);
		return result;
	}

	/**
	 * 
	 * @Title: getRealTimeHeatMapResponseObj
	 * @Description:根据响应后的json转换成所需response对象 热力图
	 * @param @param json
	 * @param @return 设定文件
	 * @return DataPushResponseParams<RealTimeHeatMap> 返回类型
	 */
	public static DataPushResponseParams<RealTimeHeatMap> getRealTimeHeatMapResponseObj(
			String json) {
		DataPushResponseParams<RealTimeHeatMap> result = new DataPushResponseParams<RealTimeHeatMap>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// List<RealTimeHeatMap> list = new ArrayList<RealTimeHeatMap>();
		if (!StringUtil.isEmpty(json))
			result = gson.fromJson(json,
					new TypeToken<DataPushResponseParams<RealTimeHeatMap>>() {
					}.getType());
		// result.setData(list);
		return result;
	}

	/**
	 * @Title: getTourTraceDistributionResponseObj
	 * @Description: 根据响应后的json转换成所需response对象 游览轨迹
	 * @param @param json
	 * @param @return 设定文件
	 * @return DataPushResponseParams<TourTraceDistribution> 返回类型
	 */
	public static DataPushResponseParams<TourTraceDistribution> getTourTraceDistributionResponseObj(
			String json) {
		DataPushResponseParams<TourTraceDistribution> result = new DataPushResponseParams<TourTraceDistribution>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<TourTraceDistribution> list = new ArrayList<TourTraceDistribution>();
		if (!StringUtil.isEmpty(json))
			result = gson
					.fromJson(
							json,
							new TypeToken<DataPushResponseParams<TourTraceDistribution>>() {
							}.getType());
		// result.setData(list);
		return result;
	}

	/**
	 * @Title: getTourTraceDistribution
	 * @Description: 游览轨迹
	 * @param @param time
	 * @param @return 设定文件
	 * @return DataPushEntity<TourTraceDistribution> 返回类型
	 */
	public static TourTraceDistributionEntity<TourTraceDistribution> getTourTraceDistribution(
			String time) {
		TourTraceDistributionEntity<TourTraceDistribution> result = DataPushClient
				.getTourTraceObj(time,
						DataPushExternalEnum.TOUR_TRACE_DISTRIBUTION);
		return result;
	}

	/**
	 * @Title: getRealTimeHeatMap
	 * @Description: 实时热力图
	 * @param @param time 接口中没有参数 传入""即可
	 * @param @return 设定文件
	 * @return DataPushEntity<DataPushDistribution> 返回类型
	 */
	public static RealTimeHeatMapEntity<RealTimeHeatMap> getRealTimeHeatMap(
			String time) {
		RealTimeHeatMapEntity<RealTimeHeatMap> result = DataPushClient
				.getRealTimeHeatMapObj(time,
						DataPushExternalEnum.REAL_TIME_HEAT_MAP);
		return result;
	}

	public static RealTimeTouristNumber getRealTimeTouristNumber() {
		String url = DataPushExternalEnum.REAL_TIME_TOURIST_NUMBER.getUrl();
		String json = HttpObject.getJsonByGet(url);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		RealTimeTouristNumber result = new RealTimeTouristNumber();
		if (!StringUtil.isEmpty(json))
			result = gson.fromJson(json,
					new TypeToken<RealTimeTouristNumber>() {
					}.getType());
		return result;
	}
}
