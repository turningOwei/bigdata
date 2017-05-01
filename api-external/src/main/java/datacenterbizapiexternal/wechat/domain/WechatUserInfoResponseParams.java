package datacenterbizapiexternal.wechat.domain;

import java.util.List;

import datacenterbizapiexternal.wechat.domain.entity.WechatUserInfo;

public class WechatUserInfoResponseParams {
	private List<WechatUserInfo> list;

	public List<WechatUserInfo> getList() {
		return list;
	}

	public void setList(List<WechatUserInfo> list) {
		this.list = list;
	}

}
