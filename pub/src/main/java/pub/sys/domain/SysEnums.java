package pub.sys.domain;

import org.springframework.data.annotation.Id;

/**
 * @ClassName: SysEnums
 * @Description: 枚举文档,常用数据
 * @author weipeng 175408322@qq.com
 * @date 2016年4月1日 上午11:58:51
 *
 */
public class SysEnums {
	@Id
	private String id;
	private String codetype;// 代码类别
	private String codeTypeName;// 代码类别名称
	private String codeColumn;// 代码字段
	private String codeColumnName;// 代码名称
	private String codeColumnValue;// 代码值
	private String codeColumnValueDescription;// 代码值含义
	private String memo;// 备注

	public String getCodetype() {
		return codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}

	public String getCodeColumn() {
		return codeColumn;
	}

	public void setCodeColumn(String codeColumn) {
		this.codeColumn = codeColumn;
	}

	public String getCodeColumnName() {
		return codeColumnName;
	}

	public void setCodeColumnName(String codeColumnName) {
		this.codeColumnName = codeColumnName;
	}

	public String getCodeColumnValue() {
		return codeColumnValue;
	}

	public void setCodeColumnValue(String codeColumnValue) {
		this.codeColumnValue = codeColumnValue;
	}

	public String getCodeColumnValueDescription() {
		return codeColumnValueDescription;
	}

	public void setCodeColumnValueDescription(String codeColumnValueDescription) {
		this.codeColumnValueDescription = codeColumnValueDescription;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
