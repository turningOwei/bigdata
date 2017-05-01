package datacenterbizapiexternal.datapush.service.impl.sort;

import java.util.HashMap;
import java.util.Map;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;

public class DataPushSortUtils {
	private static Map<String, DataPushSort> getSorts() {
		Map<String, DataPushSort> map = new HashMap<String, DataPushSort>();
		DataPushSort originCity = new OriginCitySort();
		map.put(DataPushExternalEnum.ORIGIN_CITY_DISTRIBUTION.getType(),
				originCity);

		AgeSort ageSort = new AgeSort();
		map.put(DataPushExternalEnum.AGE_DISTRIBUTION.getType(), ageSort);
		OriginProvinceSort originProvinceSort = new OriginProvinceSort();
		map.put(DataPushExternalEnum.ORIGIN_PROVINCE_DISTRIBUTION.getType(),
				originProvinceSort);
		return map;
	}

	@SuppressWarnings("static-access")
	public static DataPushEntity<DataPushDistribution> sort(
			DataPushEntity<DataPushDistribution> entity, String type) {
		Map<String, DataPushSort> map = getSorts();
		DataPushSort sortObj = map.get(type);
		if (sortObj != null)
			entity = sortObj.sort(entity);
		return entity;
	}
}
