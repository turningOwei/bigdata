package datacenterbizapiexternal.wechat.dao.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import datacenterbizapiexternal.wechat.domain.AccessTokenObj;

public interface AccessTokenObjRepository extends
		MongoRepository<AccessTokenObj, String> {

	public void deleteByName(String name);

	public AccessTokenObj findByName(String name);
}
