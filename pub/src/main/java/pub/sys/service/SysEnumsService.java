package pub.sys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.sys.domain.SysEnums;


public interface SysEnumsService {
	public Page<SysEnums> querySysEnums(SysEnums sysEnums,
										PageRequest buildPageRequest);

	public void saveOrUpdate(SysEnums sysEnums);

	public SysEnums getEntity(String id);
}
