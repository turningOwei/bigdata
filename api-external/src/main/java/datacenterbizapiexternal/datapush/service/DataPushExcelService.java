package datacenterbizapiexternal.datapush.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;

public interface DataPushExcelService {
	public File getExcel(DataPushEntity<DataPushDistribution> entity,
						 DataPushExternalEnum externalEnum, String time) throws IOException;

	public File getExcel(List<DataPushDistribution> list,
						 DataPushExternalEnum externalEnum, String startTime, String endTime)
			throws IOException;
}
