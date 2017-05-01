package bigdata.api.wifiprope.domain;

public class RealtimeInfo {
	private Long    time        ;
	private String  probeId     ;
	private Integer currVisitor ;
	private Integer totalVisitor;
	private Integer avgDwellTime;
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getProbeId() {
		return probeId;
	}
	public void setProbeId(String probeId) {
		this.probeId = probeId;
	}
	public Integer getCurrVisitor() {
		return currVisitor;
	}
	public void setCurrVisitor(Integer currVisitor) {
		this.currVisitor = currVisitor;
	}
	public Integer getTotalVisitor() {
		return totalVisitor;
	}
	public void setTotalVisitor(Integer totalVisitor) {
		this.totalVisitor = totalVisitor;
	}
	public Integer getAvgDwellTime() {
		return avgDwellTime;
	}
	public void setAvgDwellTime(Integer avgDwellTime) {
		this.avgDwellTime = avgDwellTime;
	}
	
}
