package bigdata.api.wifiprope.domain;

import java.util.List;
@Deprecated
public class WifiProbeResponse {
	private String message;
	private String state;
	private List<UVInfo> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<UVInfo> getData() {
		return data;
	}

	public void setData(List<UVInfo> data) {
		this.data = data;
	}


}
