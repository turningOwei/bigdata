package datacenterbizapiexternal.datapush.domain;

public class UrlProperties {
	/*
	 * public final static String TOURIST_TOTAL_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/persona/total"
	 * ; public final static String AGE_DISTRIBUTION_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/persona/age"
	 * ; public final static String GENDER_DISTRIBUTION_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/persona/gender"
	 * ; public final static String CONSUME_DISTRIBUTION_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/persona/consume"
	 * ; public final static String ORIGIN_PROVINCE_DISTRIBUTION_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/lbs/origin_provinces"
	 * ; public final static String ORIGIN_CITY_DISTRIBUTION_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/lbs/origin_cities"
	 * ; public final static String DURATION_ESTIMATE_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/lbs/duration"
	 * ; public final static String TOUR_TRACE_DISTRIBUTION_URL =
	 * " http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/lbs/trace"
	 * ; public final static String REAL_TIME_HEAT_MAP_URL =
	 * "http://119.37.194.87:3030/travel/data/56a73966e188ed57f87cf98f/heatmap/rt"
	 * ;
	 */
	// 119.37.194.87
	private static String ip = "115.236.20.199";
	private static String touristTotalUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/persona/total";
	private static String ageDistributionUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/persona/age";
	private static String genderDistributionUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/persona/gender";
	private static String consumeDistributionUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/persona/consume";
	private static String originProvinceDistributionUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/lbs/origin_provinces";
	private static String originCityDistributionUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/lbs/origin_cities";
	private static String durationEstimateUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/lbs/duration";
	private static String tourTraceDistributionUrl = " http://%s:3030/travel/data/56a73966e188ed57f87cf98f/lbs/trace";
	private static String realTimeHeatMapUrl = "http://%s:3030/travel/data/56a73966e188ed57f87cf98f/heatmap/rt";
	private static String realTimeTouristNumber = "http://%s:3030/yx/poi/total/570db05d98783ec6d109db83";

	public static String getTouristtotalurl() {
		return String.format(touristTotalUrl, ip);
	}

	public static String getAgedistributionurl() {
		return String.format(ageDistributionUrl, ip);
	}

	public static String getGenderdistributionurl() {
		return String.format(genderDistributionUrl, ip);
	}

	public static String getConsumedistributionurl() {
		return String.format(consumeDistributionUrl, ip);
	}

	public static String getOriginprovincedistributionurl() {
		return String.format(originProvinceDistributionUrl, ip);
	}

	public static String getOrigincitydistributionurl() {
		return String.format(originCityDistributionUrl, ip);
	}

	public static String getDurationestimateurl() {
		return String.format(durationEstimateUrl, ip);
	}

	public static String getTourtracedistributionurl() {
		return String.format(tourTraceDistributionUrl, ip);
	}

	public static String getRealtimeheatmapurl() {
		return String.format(realTimeHeatMapUrl, ip);
	}

	public static String getTouristTotalUrl() {
		return String.format(touristTotalUrl, ip);
	}

	public static void setTouristTotalUrl(String touristTotalUrl) {
		UrlProperties.touristTotalUrl = touristTotalUrl;
	}

	public static String getAgeDistributionUrl() {
		return ageDistributionUrl;
	}

	public static void setAgeDistributionUrl(String ageDistributionUrl) {
		UrlProperties.ageDistributionUrl = ageDistributionUrl;
	}

	public static String getGenderDistributionUrl() {
		return genderDistributionUrl;
	}

	public static void setGenderDistributionUrl(String genderDistributionUrl) {
		UrlProperties.genderDistributionUrl = genderDistributionUrl;
	}

	public static String getConsumeDistributionUrl() {
		return consumeDistributionUrl;
	}

	public static void setConsumeDistributionUrl(String consumeDistributionUrl) {
		UrlProperties.consumeDistributionUrl = consumeDistributionUrl;
	}

	public static String getOriginProvinceDistributionUrl() {
		return originProvinceDistributionUrl;
	}

	public static void setOriginProvinceDistributionUrl(
			String originProvinceDistributionUrl) {
		UrlProperties.originProvinceDistributionUrl = originProvinceDistributionUrl;
	}

	public static String getOriginCityDistributionUrl() {
		return originCityDistributionUrl;
	}

	public static void setOriginCityDistributionUrl(
			String originCityDistributionUrl) {
		UrlProperties.originCityDistributionUrl = originCityDistributionUrl;
	}

	public static String getDurationEstimateUrl() {
		return durationEstimateUrl;
	}

	public static void setDurationEstimateUrl(String durationEstimateUrl) {
		UrlProperties.durationEstimateUrl = durationEstimateUrl;
	}

	public static String getTourTraceDistributionUrl() {
		return tourTraceDistributionUrl;
	}

	public static void setTourTraceDistributionUrl(
			String tourTraceDistributionUrl) {
		UrlProperties.tourTraceDistributionUrl = tourTraceDistributionUrl;
	}

	public static String getRealTimeHeatMapUrl() {
		return realTimeHeatMapUrl;
	}

	public static void setRealTimeHeatMapUrl(String realTimeHeatMapUrl) {
		UrlProperties.realTimeHeatMapUrl = realTimeHeatMapUrl;
	}

	public static String getRealTimeTouristNumber() {
		return realTimeTouristNumber;
	}

	public static void setRealTimeTouristNumber(String realTimeTouristNumber) {
		UrlProperties.realTimeTouristNumber = realTimeTouristNumber;
	}

}
