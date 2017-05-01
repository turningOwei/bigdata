package datacenterbizapiexternal.datapush.domain.entity;


/**
 * 
 * @ClassName: TourTraceDistribution
 * @Description: 游览轨迹分布
 * @author weipeng 175408322@qq.com
 * @date 2016年2月29日 上午11:01:20
 *
 */
public class TourTraceDistribution implements DataPushDoc {
	private String primary;
	private String secondary;
	private String value;

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
