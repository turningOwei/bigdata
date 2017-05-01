package datacenterbizapiexternal.datapush.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import datacenterbizapiexternal.wechat.domain.entity.WechatUserInfo;

public interface WeiXinDataPushRepository extends
		MongoRepository<WechatUserInfo, String> {

}
