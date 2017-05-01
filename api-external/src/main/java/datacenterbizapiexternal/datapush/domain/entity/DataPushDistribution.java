package datacenterbizapiexternal.datapush.domain.entity;

import org.springframework.data.annotation.Transient;

public class DataPushDistribution implements DataPushDoc {
	private String key;

	private String value;

	@Transient
	private int number;

	public String getKeepValue() {
		return keepValue;
	}

	public void setKeepValue(String keepValue) {
		this.keepValue = keepValue;
	}

	@Transient
	private String keepValue;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
