package bigdata.api.wifiprope.domain;
/*	id	location
	0	整个景区/探针景点人流总和
	1	佛心广场
	2	露天弥勒
	3	白云湖
	4	江南第一大佛
	5	石城古刹
	6	卧佛殿
	7	般若谷*/
public enum ScenicSpot {
	WHOLE_AREA("0","探针景点人流总和"),
	FO_XIN_GUANG_CHANG("1","佛心广场"),
	LOU_TIAN_MI_LE("2","露天弥勒"),
	BAI_YUN_HU("3","白云湖"),
	JIANG_NAN_DI_YI_DA_FO("4","江南第一大佛"),
	SHI_CHENG_GU_CHA("5","石城古刹"),
	WO_FO_DIAN("6","卧佛殿"),
	BAN_RUO_GU("7","般若谷");
	private String probeId;
	private String name;
	private ScenicSpot(String probeId,String name){
		this.probeId = probeId;
		this.name = name;
	}
	public String getProbeId() {
		return probeId;
	}
	public String getName() {
		return name;
	}
	
	
}
