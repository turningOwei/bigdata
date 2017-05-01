package datacenterbizapiexternal.datapush.domain;

public class DataPushRequestParams {
	private String url;

	public String getFinalUrl(String timeParam) {
		return url + timeParam;
	}

}
