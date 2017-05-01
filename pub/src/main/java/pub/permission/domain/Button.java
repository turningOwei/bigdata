package pub.permission.domain;

import org.springframework.data.annotation.Transient;

/**
 * @ClassName: Button
 * @Description: 按钮资源
 * @author weipeng 175408322@qq.com
 * @date 2016年2月1日 下午1:37:55
 *
 */
public class Button {

	private String id;

	private String name;

	private String resourceStr;

	private Integer disOrder;

	private String memo;

	public String key;

	public String resourceId;
	@Transient
	public Boolean buttonCheckFlag;

	public Button() {
	}

	public Button(String name, String resourceStr, String memo) {
		this.name = name;
		this.resourceStr = resourceStr;
		this.memo = memo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceStr() {
		return resourceStr;
	}

	public void setResourceStr(String resourceStr) {
		this.resourceStr = resourceStr;
	}

	public Integer getDisOrder() {
		return disOrder;
	}

	public void setDisOrder(Integer disOrder) {
		this.disOrder = disOrder;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Boolean getButtonCheckFlag() {
		return buttonCheckFlag;
	}

	public void setButtonCheckFlag(Boolean buttonCheckFlag) {
		this.buttonCheckFlag = buttonCheckFlag;
	}

}
