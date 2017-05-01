package datacenterbizapiexternal.wechat.domain.entity;

import org.springframework.data.annotation.Id;

public class WechatUserInfo {
	@Id
	private String id;
	private String ref_date;
	private Integer user_source;
	private Integer new_user;
	private Integer cancel_user;
	private Integer cumulate_user;
	private String update_time;

	public Integer getCumulate_user() {
		return cumulate_user;
	}

	public void setCumulate_user(Integer cumulate_user) {
		this.cumulate_user = cumulate_user;
	}

	public String getRef_date() {
		return ref_date;
	}

	public void setRef_date(String ref_date) {
		this.ref_date = ref_date;
	}

	public Integer getUser_source() {
		return user_source;
	}

	public void setUser_source(Integer user_source) {
		this.user_source = user_source;
	}

	public Integer getNew_user() {
		return new_user;
	}

	public void setNew_user(Integer new_user) {
		this.new_user = new_user;
	}

	public Integer getCancel_user() {
		return cancel_user;
	}

	public void setCancel_user(Integer cancel_user) {
		this.cancel_user = cancel_user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
