package pub.user.domain;

/**
 * 用户上下文。用来代表“一群用户”在我们平台上占的“一块地盘”，用户可以在他们的“地盘”做“自管理”，比如企业内部ERP管理。
 * 
 * @author zhaowei
 *
 */
public class UserContext {

	private String id;

	private String name;

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

}
