package bigdata.api;

import bigdata.webbase.domain.StatusInfoEntity;
import bigdata.webbase.domain.StatusInfoEntity.StatusInfoCodeType;

import com.google.gson.Gson;

public abstract class AdminBaseController {

	private Gson gson = new Gson();

	/**
	 * b-jui回调信息
	 * 
	 * @param statusInfoEntity
	 * @return
	 */
	protected String getStatusInfo(StatusInfoEntity statusInfoEntity,
			StatusInfoCodeType statusInfoCodeType) {
		statusInfoEntity.setStatusCode(statusInfoCodeType.getValue());
		return gson.toJson(statusInfoEntity);
	}

}
