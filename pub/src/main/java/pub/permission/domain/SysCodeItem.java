package pub.permission.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;

import framework.util.StringUtil;

/**
 * 
 * @ClassName: SysCodeItem
 * @Description: 系统数据字典
 * @author weipeng 175408322@qq.com
 * @date 2016年2月16日 上午11:42:16
 *
 */
public class SysCodeItem {

	private String id;

	private String name;

	private String value;
	@DBRef
	private SysCode code;

	private String codeType;

	private String memo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		if (StringUtil.isEmpty(id))
			this.id = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SysCode getCode() {
		return code;
	}

	public void setCode(SysCode code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
