package datacenterbizapiexternal.datapush.domain.entity;

/**
 * 
 * @ClassName: RealTimeHeatMap
 * @Description: 实时热力图
 * @author weipeng 175408322@qq.com
 * @date 2016年2月29日 上午11:01:08
 *
 */
public class RealTimeHeatMap implements DataPushDoc {

	private String lat;

	private String lng;

	private Integer count;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
