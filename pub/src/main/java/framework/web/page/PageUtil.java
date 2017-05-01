package framework.web.page;

import framework.util.StringUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import pub.common.domain.PagerEntity;

public class PageUtil {
	/**
	 * 创建分页请求.
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pageSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}
		return new PageRequest(pageNumber - 1, pageSize, sort);
	}

	public static PageRequest buildPageRequest(PagerEntity pager,
			String sortType) {
		int pageNumber = 1;
		if (!StringUtil.isEmpty(pager.getPageCurrent())) {
			pageNumber = Integer.valueOf(pager.getPageCurrent());
		}
		int pageSize = 15;

		if (!StringUtil.isEmpty(pager.getPageSize())) {
			pageSize = Integer.valueOf(pager.getPageSize());
		}
		return buildPageRequest(pageNumber, pageSize, sortType);
	}

	public static PageRequest buildPageRequest(PagerEntity pager) {
		return buildPageRequest(pager, "auto");
	}
}
