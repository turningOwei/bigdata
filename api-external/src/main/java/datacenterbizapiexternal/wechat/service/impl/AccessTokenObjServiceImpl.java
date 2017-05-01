package datacenterbizapiexternal.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import datacenterbizapiexternal.wechat.dao.mongodb.repositories.AccessTokenObjRepository;
import datacenterbizapiexternal.wechat.domain.AccessTokenObj;
import datacenterbizapiexternal.wechat.domain.AccessTokenUrlEnum;
import datacenterbizapiexternal.wechat.service.AccessTokenObjService;

@Service
public class AccessTokenObjServiceImpl implements AccessTokenObjService {
	@Autowired
	private AccessTokenObjRepository repository;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private AccessTokenObjRepository atRepository;

	public AccessTokenObj findXcdfs() {
		return repository.findByName(AccessTokenUrlEnum.XCDFS_ACCESS_TOKEN
				.name());
	}

	public AccessTokenObj findXcly() {
		return repository.findByName(AccessTokenUrlEnum.XCLY_ACCESS_TOKEN
				.name());
	}


}
