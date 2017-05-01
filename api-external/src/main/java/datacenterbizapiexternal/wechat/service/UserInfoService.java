package datacenterbizapiexternal.wechat.service;

import java.io.IOException;
import java.util.List;

import datacenterbizapiexternal.wechat.domain.entity.WechatUserInfo;

public interface UserInfoService {

	public List<WechatUserInfo> getXcdfsRealUserInfoData(String startDate,
														 String endDate) throws IOException;

	/**
	 * @Title: getXclyRealUserInfoData
	 * @Description: 新昌旅游—微信订阅号
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return List<WechatUserInfo> 返回类型
	 */
	public List<WechatUserInfo> getXclyRealUserInfoData(String startDate,
														String endDate) throws IOException;

	public void addWechatUserInfo(WechatUserInfo userInfo);

	public List<WechatUserInfo> queryWechatUserInfoByRef_date(String date);

	public List<WechatUserInfo> queryWechatUserInfoByDate(String startDate,
														  String endDate);
}
