package datacenterbizapiexternal.datapush.domain;

public enum DataPushExternalEnum {
	REAL_TIME_TOURIST_NUMBER("realTimeTouristNumber", "实时游客总量", UrlProperties
			.getRealTimeTouristNumber()),
	//
	TOURIST_TOTAL("touritstTotal", "游客总量", UrlProperties.getTouristtotalurl()),
	//
	AGE_DISTRIBUTION("ageDistribution", "年龄分布", UrlProperties
			.getAgedistributionurl()),
	//
	GENDER_DISTRIBUTION("genderDistribution", "性别分布", UrlProperties
			.getGenderdistributionurl()),
	//
	CONSUME_DISTRIBUTION("consumeDistribution", "消费分布", UrlProperties
			.getConsumedistributionurl()),
	//
	ORIGIN_PROVINCE_DISTRIBUTION("originProvince", "来源省份分布", UrlProperties
			.getOriginprovincedistributionurl()),
	//
	ORIGIN_CITY_DISTRIBUTION("originCityDistribution", "来源城市分布", UrlProperties
			.getOrigincitydistributionurl()), DURATION_ESTIMATE(
			"durationEstimate", "停留时长估算", UrlProperties
					.getDurationestimateurl()),
	//
	TOUR_TRACE_DISTRIBUTION("tourTraceDistribution", "浏览轨迹分布", UrlProperties
			.getTourtracedistributionurl()),
	//
	REAL_TIME_HEAT_MAP("realTimeHeatMap", "实时热力图", UrlProperties
			.getRealtimeheatmapurl());
	
	private String type;
	private String url;
	private String description;

	private DataPushExternalEnum(String type, String description, String url) {
		this.type = type;
		this.description = description;
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
