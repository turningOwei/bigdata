package framework.web.page;

import java.util.List;

/**
 * 分页bean
 * 
 * @author wang xp
 * @date 2015-05-21
 * @param <T>
 */
public class PageBean {

	public static int DEFAULT_PAGESIZE = 10;
	private int pageNo; // 当前页码
	private int pageIdx; // 开始条数
	private int pageSize; // 每页行数
	private int total; // 总记录数
	private int totalPage; // 总页数
	private List<?> rows; // 数据

	private PageBean() {
		pageNo = 1;
		total = 0;
		totalPage = 0;
	}

	public static PageBean loadPage(Integer pageNo, Integer pageSize) {
		PageBean page = new PageBean();
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize != null && pageSize != 0) {
			page.pageSize = pageSize;
		} else {
			page.pageSize = DEFAULT_PAGESIZE;
		}
		page.pageIdx = pageNo * page.pageSize - page.pageSize;
		return page;
	}

	public int getPageStartIdx() {
		return pageIdx;
	}

	/**
	 * 总件数变化时，更新总页数并计算显示样式
	 */
	private void refreshPage() {
		// 总页数计算
		totalPage = total % pageSize == 0 ? total / pageSize
				: (total / pageSize + 1);
		// 防止超出最末页（浏览途中数据被删除的情况）
		if (pageNo > totalPage && totalPage != 0) {
			pageNo = totalPage;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		this.refreshPage();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		totalPage = total % pageSize == 0 ? total / pageSize
				: (total / pageSize + 1);
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
