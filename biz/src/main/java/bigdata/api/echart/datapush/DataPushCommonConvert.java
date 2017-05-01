package bigdata.api.echart.datapush;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeTouristNumber;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistribution;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;
import framework.util.CollectionUtil;

import com.google.gson.Gson;

@SuppressWarnings("rawtypes")
public class DataPushCommonConvert {

	public static Gson gson = new Gson();

	public static String getGsonOption(
			DataPushEntity<DataPushDistribution> entity,
			DataPushExternalEnum dataPushEnum, String callback) {
		// String functionName = dataPushEnum.getType();
		StringBuffer sb = new StringBuffer(callback);
		if (entity == null || CollectionUtil.isEmpty(entity.getList()))
			return sb.append("([])").toString();
		List<DataPushDistribution> list = entity.getList();
		List<Map<String, Object>> mapLst = new ArrayList<Map<String, Object>>();
		if (CollectionUtil.isNotEmpty(list)) {
			for (DataPushDistribution element : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", element.getKey());
				map.put("value", element.getValue());
				if (dataPushEnum
						.equals(DataPushExternalEnum.CONSUME_DISTRIBUTION
								.getType())) {
					map = getConsume(map, element);
				}
				mapLst.add(map);
			}
		}

		sb.append("(").append(gson.toJson(mapLst)).append(")");
		return sb.toString();
	}

	private static Map<String, Object> getConsume(Map<String, Object> map,
			DataPushDistribution element) {
		if (element.getKey().equals("高")) {
			map.put("name", "高（10000元以上）");
		} else if (element.getKey().equals("中")) {
			map.put("name", "中（3000—10000元）");
		} else if (element.getKey().equals("低")) {
			map.put("name", "低（3000元以下）");
		}
		return map;
	}

	public static String touristTotalDistrubition(List<DataPushEntity> list,
			String callback) {
		/*
		 * StringBuffer sb = new StringBuffer(
		 * DataPushExternalEnum.TOURIST_TOTAL.getType());
		 */
		StringBuffer sb = new StringBuffer(callback);
		List<HashMap<String, Object>> mapLst = new ArrayList<HashMap<String, Object>>();
		if (CollectionUtil.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				DataPushEntity entity = list.get(i);
				HashMap<String, Object> mapL = new HashMap<String, Object>();
				if (entity != null
						&& CollectionUtil.isNotEmpty(entity.getList())) {
					List elList = entity.getList();
					if (elList.size() != 1) {
					} else {
						DataPushDistribution element = (DataPushDistribution) elList
								.get(0);
						String time = entity.getTime();
						mapL.put("name", time);
						mapL.put("value", element.getValue());
						if (time.length() == 8) {
							mapL.put("year", time.substring(0, 4));
							mapL.put("month", time.substring(4, 6));
							mapL.put("day", time.substring(6, 8));
						}
					}

				}
				mapLst.add(mapL);
			}
		}
		sb.append("(").append(gson.toJson(mapLst)).append(")");
		return sb.toString();
	}

	public static String realTimeHeatMap(List<RealTimeHeatMap> list,
			String callback) {
		/*
		 * StringBuilder sb = new StringBuilder(
		 * DataPushExternalEnum.REAL_TIME_HEAT_MAP.getType());
		 */
		StringBuilder sb = new StringBuilder(callback);
		List<HashMap<String, Object>> mapLst = new ArrayList<HashMap<String, Object>>();
		if (CollectionUtil.isNotEmpty(list)) {
			for (RealTimeHeatMap element : list) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("lat", element.getLat());
				map.put("lng", element.getLng());
				map.put("count", element.getCount());
				mapLst.add(map);
			}
		}
		sb.append("(").append(gson.toJson(mapLst)).append(")");
		return sb.toString();
	}

	public static String getGsonRealTimeTouristNumberOption(
			RealTimeTouristNumber entity, String callback) {
		/*
		 * StringBuilder sb = new StringBuilder(
		 * DataPushExternalEnum.REAL_TIME_TOURIST_NUMBER.getType());
		 */

		StringBuilder sb = new StringBuilder(callback);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", entity.getTotal());
		List<HashMap<String, Object>> mapLst = new ArrayList<HashMap<String, Object>>();
		mapLst.add(map);
		sb.append("(").append(gson.toJson(mapLst)).append(")");
		return sb.toString();
	}

	public static String tourTraceDistribution(
			List<TourTraceDistributionEntity<TourTraceDistribution>> list,
			String callback) {
		StringBuffer sb = new StringBuffer(callback);
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		HashMap<String, Object> mapL = null;
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		if (CollectionUtil.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				TourTraceDistributionEntity entity = list.get(i);
				if (entity != null
						&& CollectionUtil.isNotEmpty(entity.getList())) {
					List eList = entity.getList();
					for (int j = 0; j < eList.size(); j++) {
						TourTraceDistribution element = (TourTraceDistribution) eList
								.get(j);
						int elementTotal = entity.getTotal();
						BigDecimal bigDecimal = new BigDecimal(elementTotal);
						// 算出某个人数
						int personNum = new BigDecimal(element.getValue())
								.multiply(bigDecimal).intValue();
						Integer val = hm.get(element.getSecondary()) == null ? 0
								: hm.get(element.getSecondary());
						personNum += val;
						hm.put(element.getSecondary(), personNum);
					}

				}
			}

		}

		Iterator<String> iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			mapL = new HashMap<String, Object>();
			String key = iterator.next();
			Integer val = hm.get(key);
			mapL.put("name", key);
			mapL.put("value", val);
			mapList.add(mapL);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "新昌大佛寺");
		map.put("data", mapList);
		sb.append("(").append(gson.toJson(map)).append(")");
		return sb.toString();

	}

}
