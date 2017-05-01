package pub.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pub.permission.domain.SysCodeItem;
import pub.sys.dao.SysCodeItemDao;
import pub.sys.service.SysCodeItemService;

@Service
public class SysCodeItemServiceImpl implements SysCodeItemService {
	@Autowired
	private SysCodeItemDao sysCodeItemDao;


	public Page<SysCodeItem> querySysCodeItem(SysCodeItem sysCodeItem,
			PageRequest buildPageRequest) {
		return sysCodeItemDao.querySysCodeItem(sysCodeItem, buildPageRequest);
	}


	public void saveOrUpdate(SysCodeItem sysCodeItem) {
		sysCodeItemDao.saveOrUpdate(sysCodeItem);
	}


	public void batchSave(List<SysCodeItem> itemList) {
		sysCodeItemDao.batchSave(itemList);
	}


	public SysCodeItem querySysCodeItemByCodeType(String codeType) {
		return sysCodeItemDao.querySysCodeItemByCodeType(codeType);
	}
}
