package pub.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.sys.dao.mongodb.repositories.SysEnumsRepository;
import pub.sys.domain.SysEnums;
import pub.sys.service.SysEnumsService;


public class SysEnumsServiceImpl implements SysEnumsService {
	@Autowired
	private SysEnumsRepository repository;


	public Page<SysEnums> querySysEnums(SysEnums sysEnums,
			PageRequest buildPageRequest) {
		return repository.findAll(buildPageRequest);
	}


	public void saveOrUpdate(SysEnums sysEnums) {
		repository.save(sysEnums);
	}


	public SysEnums getEntity(String id) {
		return repository.findOne(id);
	}

}
