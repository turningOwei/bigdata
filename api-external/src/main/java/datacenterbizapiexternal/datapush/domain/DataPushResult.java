package datacenterbizapiexternal.datapush.domain;

import java.util.List;

import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;

public class DataPushResult {
	private List<DataPushDistribution> dataPushData;

	public List<DataPushDistribution> getDataPushData() {
		return dataPushData;
	}

	public void setDataPushData(List<DataPushDistribution> dataPushData) {
		this.dataPushData = dataPushData;
	}

}
