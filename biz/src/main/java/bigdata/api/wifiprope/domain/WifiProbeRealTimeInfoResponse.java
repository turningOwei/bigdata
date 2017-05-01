package bigdata.api.wifiprope.domain;

import java.util.List;

public class WifiProbeRealTimeInfoResponse {
	private List<RealtimeInfo> jsondata;

	public List<RealtimeInfo> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<RealtimeInfo> jsondata) {
		this.jsondata = jsondata;
	}
	
}
