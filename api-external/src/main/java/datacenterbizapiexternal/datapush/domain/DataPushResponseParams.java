package datacenterbizapiexternal.datapush.domain;

import java.util.List;

public class DataPushResponseParams<T> {
	private String status;
	private List<T> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
