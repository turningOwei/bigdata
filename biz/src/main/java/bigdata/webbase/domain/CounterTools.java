package bigdata.webbase.domain;

public class CounterTools {

	private String id;// mongodb id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 自动递增 计数字段
	 */
	private volatile Long seq;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

}