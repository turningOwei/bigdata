package pub.common.domain;

/**
 * @ClassName: PagerEntity
 * @Description: TODO(分页实体类)
 * @author zhaowei 716517@qq.com
 * @date 2016年1月12日 下午4:20:27
 * 
 */
public class PagerEntity {

	/**
	 * @Fields total : TODO([必选] 总记录数)
	 */
	private String total;
	/**
	 * @Fields pageSize : TODO([可选] 页大小(每页记录数))
	 */
	private String pageSize;
	/**
	 * @Fields pageCurrent : TODO([可选] 当前页)
	 */
	private String pageCurrent;
	/**
	 * @Fields pageNum : TODO(显示的数字页码个数)
	 */
	private String pageNum;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
}
