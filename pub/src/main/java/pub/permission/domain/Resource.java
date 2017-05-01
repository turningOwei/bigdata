package pub.permission.domain;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * 资源
 * 
 * @author wang xp
 * @date 2015-06-02
 */
public class Resource {

	private String id;

	private String name;

	private String resourceStr;

	private Integer disOrder;

	private String memo;

	private Resource supResource;

	private String is_valid;
	@DBRef
	private List<Button> button;
	/**
	 * 记录是否添加角色
	 */
	@Transient
	private Boolean resourceFlag;
	/**
	 * 下属子资源
	 */
	@Transient
	private List<Resource> children;

	private String class1;

	private String class2;

	private String elementId;

	@Transient
	private String url;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Resource getSupResource() {
		return supResource;
	}

	public void setSupResource(Resource supResource) {
		this.supResource = supResource;
	}

	public Integer getDisOrder() {
		return disOrder;
	}

	public void setDisOrder(Integer disOrder) {
		this.disOrder = disOrder;
	}

	public Boolean getResourceFlag() {
		return resourceFlag;
	}

	public void setResourceFlag(Boolean resourceFlag) {
		this.resourceFlag = resourceFlag;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getClass2() {
		return class2;
	}

	public void setClass2(String class2) {
		this.class2 = class2;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

}
