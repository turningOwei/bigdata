package datacenterbizapiexternal.wechat.domain;

import java.util.List;

import datacenterbizapiexternal.wechat.domain.entity.WechatCumulateUserInfo;

public class WechatCumulateUserInfoResponseParams {
	private List<WechatCumulateUserInfo> list;

	public List<WechatCumulateUserInfo> getList() {
		return list;
	}

	public void setList(List<WechatCumulateUserInfo> list) {
		this.list = list;
	}

}
