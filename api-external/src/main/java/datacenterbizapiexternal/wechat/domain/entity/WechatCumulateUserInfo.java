package datacenterbizapiexternal.wechat.domain.entity;

public class WechatCumulateUserInfo {
	private String ref_date;
	private Integer user_source;
	private Integer cumulate_user;

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

	public Integer getCumulate_user() {
		return cumulate_user;
	}

	public void setCumulate_user(Integer cumulate_user) {
		this.cumulate_user = cumulate_user;
	}

}
