package pub.sys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.SysCodeItem;


public interface SysCodeItemService {

	public Page<SysCodeItem> querySysCodeItem(SysCodeItem sysCodeItem,
											  PageRequest buildPageRequest);

	public void saveOrUpdate(SysCodeItem sysCodeItem);

	public void batchSave(List<SysCodeItem> outItemList);

	public SysCodeItem querySysCodeItemByCodeType(String codeType);

}
