package bigdata.api.echart.datapush;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMapEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeTouristNumber;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistribution;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;
import datacenterbizapiexternal.datapush.service.DataPushDistributionService;
import datacenterbizapiexternal.datapush.service.RealTimeHeatMapService;
import datacenterbizapiexternal.datapush.service.TourTraceDistributionService;
//import datacenterbizapiexternal.ziyoubaoticketbiz.service.KindDailySaleService;
import framework.util.date.DateStyle;
import framework.util.date.DateUtil;
import pub.common.domain.PagerEntity;
import  bigdata.api.AdminBaseController;
import bigdata.common.NormalDistribution;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/echart/datapush")
public class DataPushEchartController extends AdminBaseController {
	private Logger log = LoggerFactory
			.getLogger(DataPushEchartController.class);

	@Autowired
	private DataPushDistributionService<DataPushDistribution> dataPushDistributionService;
	@Autowired
	private TourTraceDistributionService tourTraceDistributionService;
	@Autowired
	private RealTimeHeatMapService realTimeHeatMapService;
	/*@Autowired
	private KindDailySaleService kindDailySaleService;*/

	// 查询年龄分布
	@RequestMapping(params = { "method=queryAgeDistribution" })
	public String queryAgeDistribution(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/ageDistribution";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=queryAgeDistributionView" })
	public String queryAgeDistributionView(ModelMap model, String time,
			String enumName) {
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		if (entity == null) {
			model.addAttribute("list", null);
		} else {
			model.addAttribute("list", entity.getList());
		}
		return "echart/datapush/ageDistributionList";
	}

	// 查询性别分布
	@RequestMapping(params = { "method=queryGenderDistribution" })
	public String queryGenderDistribution(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/genderDistribution";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=queryGenderDistributionView" })
	public String queryGenderDistributionView(ModelMap model, String time,
			String enumName) {
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		if (entity == null) {
			model.addAttribute("list", null);
		} else {
			model.addAttribute("list", entity.getList());
		}
		return "echart/datapush/genderDistributionList";
	}

	// 查询来源省份分布
	@RequestMapping(params = { "method=queryOriginProvinceDistribution" })
	public String queryOriginProvinceDistribution(ModelMap model,
			PagerEntity pager, String time, String enumName) {
		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/originProvinceDistribution";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=queryOriginProvinceDistributionView" })
	public String queryOriginProvinceDistributionView(ModelMap model,
			String time, String enumName) {
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		if (entity == null) {
			model.addAttribute("list", null);
		} else {
			model.addAttribute("list", entity.getList());
		}
		return "echart/datapush/originProvinceDistributionList";
	}

	// 查询来源城市分布
	@RequestMapping(params = { "method=queryOriginCityDistribution" })
	public String queryOriginCityDistribution(ModelMap model,
			PagerEntity pager, String time, String enumName) {
		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/originCityDistribution";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=queryOriginCityDistributionView" })
	public String queryOriginCityDistributionView(ModelMap model, String time,
			String enumName) {
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		if (entity == null) {
			model.addAttribute("list", null);
		} else {
			model.addAttribute("list", entity.getList());
		}
		return "echart/datapush/originCityDistributionList";
	}

	// 查询消费分布
	@RequestMapping(params = { "method=queryConsumeDistribution" })
	public String queryConsumeDistribution(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/consumeDistribution";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=queryConsumeDistributionView" })
	public String queryConsumeDistributionView(ModelMap model, String time,
			String enumName) {
		if (StringUtils.isEmpty(time))
			time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
					"yyyyMM");
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		model.addAttribute("time", time);
		model.addAttribute("enumName", enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		if (entity == null) {
			model.addAttribute("list", null);
		} else {
			model.addAttribute("list", entity.getList());
		}
		return "echart/datapush/consumeDistributionList";
	}

	// 查询游客量统计分布
	@RequestMapping(params = { "method=queryTouristDayTotalDistribution" })
	public String queryTouristDayTotalDistribution(ModelMap model,
			PagerEntity pager, String startTime, String endTime, String enumName) {

		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			startTime = DateUtil.DateToString(DateUtil.addDay(new Date(), -38),
					"yyyyMMdd");
			endTime = DateUtil.DateToString(DateUtil.addDay(new Date(), -30),
					"yyyyMMdd");
		}
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/touristDayTotalDistribution";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(params = { "method=queryTouristDayTotalDistributionView" })
	public String queryTouristDayTotalDistributionView(ModelMap model,
			String startTime, String endTime, String enumName) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isEmpty(startTime))
			startTime = DateUtil.DateToString(DateUtil.addDay(new Date(), -38),
					"yyyyMMdd");
		if (StringUtils.isEmpty(endTime))
			endTime = DateUtil.DateToString(DateUtil.addDay(new Date(), -30),
					"yyyyMMdd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date start = sdf.parse(startTime);
		Date end = sdf.parse(endTime);
		List<Date> Date1 = findDates(start, end);
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("enumName", enumName);
		String time = null;
		List<DataPushDistribution> list1 = new ArrayList<DataPushDistribution>();
		Map<String, String> map = new LinkedHashMap<String, String>();
		DataPushDistribution d = null;
		String t = null;
		String s = null;
		for (Date date : Date1) {
			time = sdf.format(date);
			DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
					.queryEntityByTimeAndType(time, dataPushEnum.getType());
			if (entity == null) {
				model.addAttribute("map", null);
			} else {
				t = entity.getTime();
				list1 = entity.getList();
				d = list1.get(0);
				if (d == null) {
					model.addAttribute("map", null);
				} else {
					s = d.getValue();
					map.put(t, s);
				}

			}
		}

		model.addAttribute("map", map);
		return "echart/datapush/touristDayTotalDistributionList";
	}

	// 查询轨迹分布
	@RequestMapping(params = { "method=queryTourTraceDistribution" })
	public String queryTourTraceDistribution(ModelMap model, PagerEntity pager,
			String startTime, String endTime, String enumName) {
		// 没有日期,默认前一个月
		if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			startTime = DateUtil.DateToString(DateUtil.addDay(new Date(), -38),
					"yyyyMMdd");
			endTime = DateUtil.DateToString(DateUtil.addDay(new Date(), -30),
					"yyyyMMdd");
		}
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("enumName", enumName);
		return "echart/datapush/tourTraceDistribution";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=ageDistributionJson" })
	@ResponseBody()
	public String ageDistributionJson(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		return DataPushConvert.getGsonPieOrFunnelOption(entity).toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=genderDistributionJson" })
	@ResponseBody()
	public String genderDistributionJson(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		return DataPushConvert.getGsonPieOrFunnelOption(entity).toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=originProvinceDistributionJson" })
	@ResponseBody()
	public String originProvinceDistributionJson(ModelMap model,
			PagerEntity pager, String time, String enumName) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		return DataPushConvert.getGsonBarOrLineOption(entity, dataPushEnum)
				.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=originCityDistributionJson" })
	@ResponseBody()
	public String originCityDistributionJson(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		return DataPushConvert.getGsonBarOrLineOption(entity, dataPushEnum)
				.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=consumeDistributionJson" })
	@ResponseBody()
	public String consumeDistributionJson(ModelMap model, PagerEntity pager,
			String time, String enumName) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		return DataPushConvert.getGsonPieOrFunnelOption(entity).toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = { "method=touristDayTotalDistributionJson" })
	@ResponseBody()
	public String touristDayTotalDistributionJson(ModelMap model,
			PagerEntity pager, String enumName, String startTime, String endTime) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		List<DataPushEntity> list = dataPushDistributionService
				.touristTotalDistrubition(startTime, endTime);
		return DataPushConvert.getGsonBarOrLineOption(list, dataPushEnum)
				.toString();
	}

	// 当前游客人数
	@RequestMapping(params = { "method=realTimeTouristNumber" })
	@ResponseBody()
	public String realTimeTouristNumber(ModelMap model, String time,
			String callback) {
		RealTimeTouristNumber entity = dataPushDistributionService
				.realTimeTouristNumber();
		return DataPushCommonConvert.getGsonRealTimeTouristNumberOption(entity,
				callback);

	}

	// 游客总量
	@RequestMapping(params = { "method=touritstDayTotalDistribution" })
	@ResponseBody()
	public String touritstDayTotalDistribution(ModelMap model,
			PagerEntity pager, String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.TOURIST_TOTAL, callback);
	}

	// 年龄分布
	@RequestMapping(params = { "method=ageDistribution" })
	@ResponseBody()
	public String ageDistribution(ModelMap model, PagerEntity pager,
			String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.AGE_DISTRIBUTION, callback);
	}

	// 性别分布
	@RequestMapping(params = { "method=genderDistribution" })
	@ResponseBody()
	public String genderDistribution(ModelMap model, PagerEntity pager,
			String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.GENDER_DISTRIBUTION, callback);
	}

	// 消费分布
	@RequestMapping(params = { "method=consumeDistribution" })
	@ResponseBody()
	public String consumeDistribution(ModelMap model, PagerEntity pager,
			String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.CONSUME_DISTRIBUTION, callback);
	}

	// 来源城市分布
	@RequestMapping(params = { "method=originCityDistribution" })
	@ResponseBody()
	public String originCityDistribution(ModelMap model, PagerEntity pager,
			String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.ORIGIN_CITY_DISTRIBUTION, callback);
	}

	// 来源省份
	@RequestMapping(params = { "method=originProvinceDistribution" })
	@ResponseBody()
	public String originProvinceDistribution(ModelMap model, PagerEntity pager,
			String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.ORIGIN_PROVINCE_DISTRIBUTION, callback);
	}

	@RequestMapping(params = { "method=durationEstimate" })
	@ResponseBody()
	public String durationEstimate(ModelMap model, PagerEntity pager,
			String time, String callback) {
		return distributionJson(model, pager, time,
				DataPushExternalEnum.DURATION_ESTIMATE, callback);
	}

	// 轨迹分布)month可选，season可选，year必传
	@RequestMapping(params = { "method=tourTraceDistribution" })
	@ResponseBody()
	public String tourTraceDistribution(String year, String month,
			String season, String callback) {
		String tourTraceTime = getTourTraceTime(year, month, season);
		String startTime = tourTraceTime.split(",")[0];
		String endTime = tourTraceTime.split(",")[1];
		List<TourTraceDistributionEntity<TourTraceDistribution>> list = tourTraceDistributionService
				.queryEntityByStartTimeAndEndTime(startTime, endTime);

		return DataPushCommonConvert.tourTraceDistribution(list, callback);
	}

	// 客流统计
	@RequestMapping(params = { "method=touristTotalDistrubition" })
	@ResponseBody()
	public String touristTotalDistrubition(String startTime, String endTime,
			String callback) {
		Date startDate = DateUtil.StringToDate(startTime, DateStyle.YYYY_MM_DD);
		Date endDate = DateUtil.StringToDate(endTime, DateStyle.YYYY_MM_DD);
		startTime = DateUtil.DateToString(startDate, DateStyle.YYYYMMDD);
		endTime = DateUtil.DateToString(endDate, DateStyle.YYYYMMDD);
		List<DataPushEntity> list = dataPushDistributionService
				.touristTotalDistrubition(startTime, endTime);
		return DataPushCommonConvert.touristTotalDistrubition(list, callback);
	}

	// 热力图
	@RequestMapping(params = { "method=realTimeHeatMap" })
	@ResponseBody()
	public String realTimeHeatMap(String startTime, String callback) {
		Date date = DateUtil
				.StringToDate(startTime, DateStyle.YYYY_MM_DD_HH_MM);
		RealTimeHeatMapEntity<RealTimeHeatMap> entity = realTimeHeatMapService
				.queryEntityByStartTime(date);
		List<RealTimeHeatMap> list = new ArrayList<RealTimeHeatMap>();
		if (entity != null)
			list = entity.getList();
		return DataPushCommonConvert.realTimeHeatMap(list, callback);
	}

	/**
	 * @Title: distributionJson
	 * @Description: 通用类型
	 * @param @param model
	 * @param @param pager
	 * @param @param time
	 * @param @param dataPushEnum
	 * @param @return json {name:"",value:""}
	 * @return String 返回类型
	 */
	@SuppressWarnings("unchecked")
	private String distributionJson(ModelMap model, PagerEntity pager,
			String time, DataPushExternalEnum dataPushEnum, String callback) {
		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		return DataPushCommonConvert.getGsonOption(entity, dataPushEnum,
				callback);
	}

	public static List<Date> findDates(Date start, Date end) {
		List Date1 = new ArrayList();
		Date1.add(start);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(end);
		// 测试此日期是否在指定日期之后
		while (end.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			Date1.add(calBegin.getTime());
		}
		return Date1;
	}

	// 轨迹分布：根据年月日 得到startTime,endTime
	private String getTourTraceTime(String year, String month, String season) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		sdf.format(date);

		String startTime = null;
		String endTime = null;
		if (year == null || year.trim().equals("")) {
			year = DateUtil.getYear(date) + "";
		}
		if ((month == null || month.trim().equals(""))
				&& (season == null || season.trim().equals(""))) {// 取上一月
			int m = DateUtil.getMonth(date) - 1;
			if (m < 10) {
				month = "0" + m;
			}
		}

		if (month == null || month.trim().equals("")) {
			if (season.equals("1")) {
				startTime = year + "01";
				endTime = year + "03";
			} else if (season.equals("2")) {
				startTime = year + "04";
				endTime = year + "06";
			} else if (season.equals("3")) {
				startTime = year + "07";
				endTime = year + "09";
			} else {
				startTime = year + "10";
				endTime = year + "12";
			}
			return startTime + "," + endTime;
		}
		startTime = year + month;
		endTime = year + month;
		return startTime + "," + endTime;
	}

	// 生成迁徒轨迹excel
	@RequestMapping(params = { "method=tourTraceDistributionExcel" })
	@ResponseBody()
	public void tourTraceDistribution(String year, String month, String season,
			HttpServletResponse response) throws IOException {
		String json = tourTraceDistribution(year, month, season, "");
		json = json.substring(1, json.length() - 1);
		List<Map> data = JSONArray.toJavaObject(JSONObject.parseObject(json)
				.getJSONArray("data"), List.class);

		String title = year + "年" + month + "月" + "游客迁徙轨迹";
		File excel = tourTraceDistributionService.getTraceExcel(title, data);
		NormalDistribution.normalDistribution(title, excel, response, log);
	}

	// 生成游客数据excel
	@RequestMapping(params = { "method=customerExcel" })
	@ResponseBody()
	public void customerExcel(ModelMap model, PagerEntity pager, String time,
			HttpServletResponse response) {
		String consumeJson = consumeDistribution(model, pager, time, "");
		consumeJson = consumeJson.substring(1, consumeJson.length() - 1);// 去掉jsonp的括号
		String ageJson = ageDistribution(model, pager, time, "");
		ageJson = ageJson.substring(1, ageJson.length() - 1);// 去掉jsonp的括号
		String genderJson = genderDistribution(model, pager, time, "");
		genderJson = genderJson.substring(1, genderJson.length() - 1);// 去掉jsonp的括号
		List<Map<String, String>> consume = JSONArray.toJavaObject(
				JSONObject.parseArray(consumeJson), List.class);

		List<Map<String, String>> age = JSONArray.toJavaObject(
				JSONObject.parseArray(ageJson), List.class);
		List<Map<String, String>> gender = JSONArray.toJavaObject(
				JSONObject.parseArray(genderJson), List.class);
		time = time.substring(0, 4) + "年" + time.substring(4) + "月";
		String title = time + "新昌县游客用户画像";
		File excel = dataPushDistributionService.getExcel(consume, age, gender,
				title, time);

		NormalDistribution.normalDistribution(title, excel, response, log);
	}
}
