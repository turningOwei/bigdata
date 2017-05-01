package datacenterbizapiexternal.datapush.service.impl.sort;

import java.util.List;

import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import framework.util.CollectionUtil;

public abstract class DataPushSort {
	public static DataPushEntity<DataPushDistribution> sort(
			DataPushEntity<DataPushDistribution> entity) {
		if (entity == null)
			return entity;
		List<DataPushDistribution> list = entity.getList();
		if (CollectionUtil.isNotEmpty(list) && list.size() > 1) {
			// 直接插入排序
			for (int i = 1; i < list.size(); i++) {
				// 待插入元素
				DataPushDistribution temp = list.get(i);
				int j;
				for (j = i - 1; j >= 0; j--) {
					if (getValue(list.get(j)) < getValue(temp)) {
						list.set(j + 1, list.get(j));
					} else {
						break;
					}
				}
				list.set(j + 1, temp);
			}
		}
		return entity;
	}

	public static Double getValue(DataPushDistribution pojo) {
		return Double.parseDouble(pojo.getValue());
	}
}
