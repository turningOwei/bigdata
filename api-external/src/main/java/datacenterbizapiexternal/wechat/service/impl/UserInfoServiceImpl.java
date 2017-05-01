package datacenterbizapiexternal.wechat.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import datacenterbizapiexternal.datapush.dao.mongodb.repositories.WeiXinDataPushRepository;
import datacenterbizapiexternal.wechat.WechatClient;
import datacenterbizapiexternal.wechat.dao.mongodb.repositories.AccessTokenObjRepository;
import datacenterbizapiexternal.wechat.domain.AccessTokenObj;
import datacenterbizapiexternal.wechat.domain.AccessTokenUrlEnum;
import datacenterbizapiexternal.wechat.domain.WechatCumulateUserInfoResponseParams;
import datacenterbizapiexternal.wechat.domain.WechatUserInfoResponseParams;
import datacenterbizapiexternal.wechat.domain.entity.WechatCumulateUserInfo;
import datacenterbizapiexternal.wechat.domain.entity.WechatUserInfo;
import datacenterbizapiexternal.wechat.service.UserInfoService;
import framework.util.CollectionUtil;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private WeiXinDataPushRepository repository;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private AccessTokenObjRepository atRepository;

	public List<WechatUserInfo> getXcdfsRealUserInfoData(String startDate,
			String endDate) throws IOException {
		AccessTokenObj accessTokenObj = WechatClient.getXcdfsAccessToken();
		accessTokenObj.setName(AccessTokenUrlEnum.XCDFS_ACCESS_TOKEN.name());
		accessTokenObj.setMemo("新昌大佛寺—微信服务号");
		atRepository.deleteByName(accessTokenObj.getName());
		mongoTemplate.insert(accessTokenObj);
		return getRealUserInfoData(startDate, endDate, accessTokenObj);
	}

	public List<WechatUserInfo> getXclyRealUserInfoData(String startDate,
			String endDate) throws IOException {
		AccessTokenObj accessTokenObj = WechatClient.getXclyAccessToken();
		accessTokenObj.setName(AccessTokenUrlEnum.XCLY_ACCESS_TOKEN.name());
		accessTokenObj.setMemo("新昌旅游—微信订阅号");
		atRepository.deleteByName(accessTokenObj.getName());
		mongoTemplate.insert(accessTokenObj);
		return getRealUserInfoData(startDate, endDate, accessTokenObj);
	}

	private List<WechatUserInfo> getRealUserInfoData(String startDate,
			String endDate, AccessTokenObj accessTokenObj) throws IOException {

		List<WechatUserInfo> result = new ArrayList<WechatUserInfo>();
		WechatUserInfoResponseParams userInfoParams = WechatClient.getUserInfo(
				accessTokenObj.getAccess_token(), startDate, endDate);
		WechatCumulateUserInfoResponseParams userCumulateParams = WechatClient
				.getUserCumulate(accessTokenObj.getAccess_token(), startDate,
						endDate);
		result = mergeData(result, userInfoParams, userCumulateParams);
		return result;
	}

	private List<WechatUserInfo> mergeData(List<WechatUserInfo> result,
			WechatUserInfoResponseParams userInfoParams,
			WechatCumulateUserInfoResponseParams userCumulateParams) {
		List<WechatUserInfo> userInfoList = userInfoParams.getList();
		List<WechatCumulateUserInfo> userCumulateList = userCumulateParams
				.getList();
		Map<String, Integer> newUserMap = new HashMap<String, Integer>();
		Map<String, Integer> cancelUserMap = new HashMap<String, Integer>();
		if (CollectionUtil.isNotEmpty(userInfoList)) {
			for (WechatUserInfo userInfo : userInfoList) {
				Integer newUserVal = newUserMap.get(userInfo.getRef_date());
				if (newUserVal == null) {
					newUserMap.put(userInfo.getRef_date(),
							userInfo.getNew_user());
				} else {
					newUserVal += userInfo.getNew_user();
					newUserMap.put(userInfo.getRef_date(), newUserVal);
				}
				Integer cancelUserVal = cancelUserMap.get(userInfo
						.getRef_date());
				if (cancelUserVal == null) {
					cancelUserMap.put(userInfo.getRef_date(),
							userInfo.getCancel_user());
				} else {
					cancelUserVal += userInfo.getCancel_user();
					cancelUserMap.put(userInfo.getRef_date(), cancelUserVal);
				}

			}
		}
		if (CollectionUtil.isNotEmpty(userCumulateList)) {
			for (WechatCumulateUserInfo userCumulate : userCumulateList) {
				WechatUserInfo entity = new WechatUserInfo();
				entity.setRef_date(userCumulate.getRef_date());
				entity.setCumulate_user(userCumulate.getCumulate_user());
				entity.setNew_user(newUserMap.get(userCumulate.getRef_date()));
				entity.setCancel_user(cancelUserMap.get(userCumulate
						.getRef_date()));
				result.add(entity);
			}
		}
		return result;
	}

	public void addWechatUserInfo(WechatUserInfo userInfo) {
		repository.insert(userInfo);

	}

	public List<WechatUserInfo> queryWechatUserInfoByRef_date(String date) {

		List<? extends WechatUserInfo> list = mongoTemplate.find(new Query(
				new Criteria("ref_date").is(date)), WechatUserInfo.class);

		return (List<WechatUserInfo>) list;
	}

	public List<WechatUserInfo> queryWechatUserInfoByDate(String beginDate,
			String endDate) {
		List<? extends WechatUserInfo> dataList = mongoTemplate.find(
				query(where("ref_date").gte(beginDate).lte(endDate)),
				new WechatUserInfo().getClass());
		return (List<WechatUserInfo>) dataList;
	}

}
