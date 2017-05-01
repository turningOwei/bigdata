package datacenterbizapiexternal.datapush.domain.entity;

import java.util.List;

public class RealTimeHeatTouristMap<T> {
	private Integer status;
	private Integer total;
	private List<T> data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
