package bigdata.task;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import datacenterbizapiexternal.datapush.DataPushClient;
import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMap;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeHeatMapEntity;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistribution;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;
import datacenterbizapiexternal.datapush.service.DataPushDistributionService;
import datacenterbizapiexternal.datapush.service.RealTimeHeatMapService;
import datacenterbizapiexternal.datapush.service.TourTraceDistributionService;
/*import datacenterbizapiexternal.infrared.InfraRedClient;
import datacenterbizapiexternal.infrared.domain.InfraRedIPS;
import datacenterbizapiexternal.infrared.domain.entity.InfrRedHistoryDataVerification;
import datacenterbizapiexternal.infrared.domain.entity.InfraRedHistoryData;
import datacenterbizapiexternal.infrared.domain.responseparams.InfraRedResponse;
import datacenterbizapiexternal.infrared.service.InfraRedService;
import datacenterbizapiexternal.videopassenger.VideoPassengerClient;
import datacenterbizapiexternal.videopassenger.domain.entity.VideoPassengerTimeSum;
import datacenterbizapiexternal.videopassenger.domain.entity.VideoPassengerTimeValue;
import datacenterbizapiexternal.videopassenger.domain.responseParams.VideoPassengerResponseParams;
import datacenterbizapiexternal.videopassenger.service.VideoPassengerService;
import datacenterbizapiexternal.videopassenger.service.VideoPassengerTimeValueService;*/
import framework.util.date.DateStyle;
import framework.util.date.DateUtil;



@SuppressWarnings({ "unchecked", "rawtypes" })
public class DataPushTask {
	private Logger logger = LoggerFactory.getLogger(DataPushTask.class);
	@Autowired
	private DataPushDistributionService dataPushDistributionService;
	@Autowired
	private RealTimeHeatMapService realTimeHeatMapService;
	@Autowired
	private TourTraceDistributionService tourTraceDistributionService;
	/*@Autowired
	private VideoPassengerTimeValueService videoPassengerTimeValueService;
	@Autowired
	private InfraRedService infraRedService;
	@Autowired
	private VideoPassengerService videoPassengerService;*/
	static boolean flg = false, flgs = false;
	static int count = 0;

	public List<DataPushEntity<DataPushDistribution>> listAddDataPushEnity(
			String time, DataPushExternalEnum dataPushEnum,
			List<DataPushEntity<DataPushDistribution>> list) {
		DataPushEntity enity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		if (enity == null) {
			DataPushEntity<DataPushDistribution> distribution = DataPushClient
					.getDataPushObj(time, dataPushEnum);
			if (distribution != null)
				list.add(distribution);
		}
		return list;
	}

	public List<DataPushEntity<DataPushDistribution>> batchListAddDataPushEnity(
			String time, List<DataPushEntity<DataPushDistribution>> list) {

		list = listAddDataPushEnity(time, DataPushExternalEnum.TOURIST_TOTAL,
				list);
		list = listAddDataPushEnity(time,
				DataPushExternalEnum.AGE_DISTRIBUTION, list);
		list = listAddDataPushEnity(time,
				DataPushExternalEnum.GENDER_DISTRIBUTION, list);
		list = listAddDataPushEnity(time,
				DataPushExternalEnum.CONSUME_DISTRIBUTION, list);
		list = listAddDataPushEnity(time,
				DataPushExternalEnum.ORIGIN_PROVINCE_DISTRIBUTION, list);
		list = listAddDataPushEnity(time,
				DataPushExternalEnum.ORIGIN_CITY_DISTRIBUTION, list);
		list = listAddDataPushEnity(time,
				DataPushExternalEnum.DURATION_ESTIMATE, list);
		return list;
	}

	/**
	 * @Title: dataPushDistribution
	 * @Description: 个推各种分布数据获取
	 * @param
	 * @return void 返回类型
	 */
	public void dataPushDistributionTask() {
		// logger.info("执行DataPushTask.dataPushDistributionTask开始");
		/**
		 * 根据个推提供 数据获取最晚上个月
		 */
		String time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
				"yyyyMM");
		dataPushDistributionTask(time);
		// logger.info("执行DataPushTask.dataPushDistributionTask结束");
	}

	/**
	 * @Title: dataPushDistributionTask2
	 * @Description: 每天游客总量
	 * @param
	 * @return void 返回类型
	 */
	public void dataPushDistributionTask2() {
		// logger.info("执行DataPushTask.dataPushDistributionTask开始");
		/**
		 * 根据个推提供 数据获取最晚上个月
		 */
		String time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
				"yyyyMM");
		dataPushDistributionTask(time);
		// logger.info("执行DataPushTask.dataPushDistributionTask结束");
	}

	public void dataPushDistributionTask(String time) {
		List<DataPushEntity<DataPushDistribution>> list = new ArrayList<DataPushEntity<DataPushDistribution>>();

		list = batchListAddDataPushEnity(time, list);

		if (list.size() > 0) {
			dataPushDistributionService.addDataPushEntities(list);
			String distributions = "";
			for (DataPushEntity<DataPushDistribution> dataPushEntity : list) {
				distributions += dataPushEntity.getDescription() + ",";
			}
			logger.info("数据时间为" + time + "的相关" + distributions
					+ "分布数据,更新到本地数据库");
		} else {
			logger.warn("执行DataPushTask.dataPushDistributionTask结束,未添加个推数据到数据库");
		}
	}

	/**
	 * 
	 * @Title: tourTraceDistributionTask
	 * @Description: 游览轨迹数据获取
	 * @param
	 * @return void 返回类型
	 */
	public void tourTraceDistributionTask() {
		// logger.info("执行DataPushTask.tourTraceDistributionTask开始");
		String time = DateUtil.DateToString(DateUtil.addMonth(new Date(), -1),
				"yyyyMM");
		TourTraceDistributionEntity<TourTraceDistribution> tourTraceDistribution = DataPushClient
				.getTourTraceDistribution(time);
		DataPushEntity enity = dataPushDistributionService
				.queryEntityByTimeAndType(time,
						DataPushExternalEnum.TOUR_TRACE_DISTRIBUTION.getType());
		int totle = getTotle(time);
		if (enity == null) {
			tourTraceDistribution.setTotal(totle);
			tourTraceDistributionService.addEntity(tourTraceDistribution);
			logger.info("数据时间为" + time + "的游览轨迹数据,更新到本地数据库");
		}
		logger.info("执行DataPushTask.tourTraceDistributionTask结束");
	}

	// 获取总人数
	public int getTotle(String time) {
		DataPushEntity<DataPushDistribution> distribution = DataPushClient
				.getDataPushObj(time, DataPushExternalEnum.TOURIST_TOTAL);
		if (null != distribution && distribution.getList() != null
				&& distribution.getList().size() > 0) {
			String value = distribution.getList().get(0).getValue();
			return Integer.parseInt(value);
		}
		return 0;

	}

	/**
	 * @Title: realTimeHeatTask
	 * @Description: 实时热力图数据
	 * @param
	 * @return void 返回类型
	 */
	public void realTimeHeatTask() {
		// logger.info("执行DataPushTask.realTimeHeatTask开始");
		String time = DateUtil.getRealTimeHeatMapHttpStart(new Date());
		RealTimeHeatMapEntity entity = realTimeHeatMapService
				.queryEntityByStartTimeThroughDB(new Date());
		if (entity == null) {
			RealTimeHeatMapEntity<RealTimeHeatMap> realTimeHeatMap = DataPushClient
					.getRealTimeHeatMap("");
			realTimeHeatMapService.addEntity(realTimeHeatMap);
			logger.info("数据时间为" + time + "的实时热力图数据,更新到本地数据库");
		}
		// logger.info("执行DataPushTask.realTimeHeatTask结束");
	}

	/**
	 * @Title: dailyTouristTotal
	 * @Description: 次日凌晨同步昨日日游客总量
	 * @param
	 * @return void 返回类型
	 */
	public void dailyTouristTotal() {
		String time = DateUtil.DateToString(DateUtil.addDay(new Date(), -3),
				"yyyyMMdd");
		dailyTouristTotal(time);
	}

	public void dailyTouristTotal(String time) {
		DataPushEntity enity = dataPushDistributionService
				.queryEntityByTimeAndType(time,
						DataPushExternalEnum.TOURIST_TOTAL.getType());
		if (enity == null) {
			DataPushEntity<DataPushDistribution> distribution = DataPushClient
					.getDataPushObj(time, DataPushExternalEnum.TOURIST_TOTAL);
			if (distribution != null)
				dataPushDistributionService.addDataPushEntity(distribution);
		}
	}

	/*public void saveRandomData() throws ParseException {
		VideoPassengerTimeValue value = new VideoPassengerTimeValue();
		String date = DateUtil.DateToString(new Date(), DateUtil.getYMDH)
				+ ":00";
		Date nowDate = DateUtil.StringToDate(date);
		Date cDate = DateUtil.StringToDate(DateUtil.DateToString(new Date(),
				DateUtil.getYMD) + " 21:00");
		Date zDate = DateUtil.StringToDate(DateUtil.DateToString(new Date(),
				DateUtil.getYMD) + " 00:00");
		String time = DateUtil.StringToString(date, "HH:mm");

		value.setTime(date);
		value.setValue(0);
		VideoPassengerResponseParams<VideoPassengerTimeSum> response = VideoPassengerClient
				.getTouristOnTime(DateUtil.DateToString(new Date(),
						DateStyle.YYYY_MM_DD));
		List<VideoPassengerTimeSum> list = response.getList();
		int total = 50;
		for (VideoPassengerTimeSum sum : list) {
			if (time.contains(sum.getKey())) {
				total = sum.getValue();
			}
		}

		// if (zDate.equals(nowDate) || nowDate.after(cDate)
		// || nowDate.equals(cDate)) {
		*//*value.setValue(videoPassengerService.getRandomData(value.getTime(),
				total));*//*
		value.setValue(videoPassengerService.getRandomDataVer2(value.getTime(),
				total));
		
		// }
		videoPassengerTimeValueService.insert(value);

	}*/

	/*public void pushTodayData() throws IOException {
		String startDate = DateUtil.DateToString(
				DateUtil.addDay(new Date(), +0), "yyyy-MM-dd");
		String endDate = DateUtil.DateToString(DateUtil.addDay(new Date(), +0),
				"yyyy-MM-dd");
		String dateStr = "yyyy-MM-dd";
		pushHistoryData(startDate, endDate, dateStr);
	}*/

	/*public void pushHistoryData() throws IOException {
		String startDate = DateUtil.DateToString(
				DateUtil.addDay(new Date(), -1), "yyyy-MM-dd");
		String endDate = DateUtil.DateToString(DateUtil.addDay(new Date(), -1),
				"yyyy-MM-dd");
		String dateStr = "yyyy-MM-dd";
		pushHistoryData(startDate, endDate, dateStr);
	}

	public void pushHistoryData(String startTime, String endTime, String dateStr) {

		ArrayList<InfraRedResponse<ArrayList<InfraRedHistoryData>>> result = InfraRedClient
				.getHistoryData(InfraRedIPS.IP252.getIp(),
						startTime + " 00:00", endTime + " 23:59");
		ArrayList<InfraRedResponse<ArrayList<InfraRedHistoryData>>> result1 = InfraRedClient
				.getHistoryData(InfraRedIPS.IP249.getIp(),
						startTime + " 00:00", endTime + " 23:59");
		infraRedService.addHistoryData(result, startTime);
		infraRedService.addHistoryData(result1, startTime);

	}*/

	/*public void dateVerification() {

		Thread1 t = new Thread1();
		t.start();
	}*/

	/*class Thread1 extends Thread {

		@Override
		public void run() {
			String startTime = DateUtil.DateToString(
					DateUtil.addDay(new Date(), -1), "yyyy-MM-dd");
			System.out.println(startTime);
			Date datetime = DateUtil.StringToDate(DateUtil.DateToString(
					new Date(), "yyyy-MM-dd") + " 05:00");
			System.out.println(datetime);
			while (true) {
				ArrayList<InfraRedResponse<ArrayList<InfraRedHistoryData>>> result = InfraRedClient
						.getHistoryData(InfraRedIPS.IP252.getIp(), startTime
								+ " 00:00", startTime + " 23:59");
				ArrayList<InfraRedResponse<ArrayList<InfraRedHistoryData>>> result1 = InfraRedClient
						.getHistoryData(InfraRedIPS.IP249.getIp(), startTime
								+ " 00:00", startTime + " 23:59");
				if (result.get(0).getData().size() != 0
						&& result1.get(0).getData().size() != 0) {
					return;
				}
				if (new Date().after(datetime)) {
					String str1 = "未提供数据";
					String str2 = "提供数据";
					InfrRedHistoryDataVerification dateVerification = new InfrRedHistoryDataVerification();
					if (result.get(0).getData().size() == 0
							&& result1.get(0).getData().size() == 0) {
						dateVerification.setIp1(result.get(0).getIp());
						dateVerification.setIp2(result1.get(0).getIp());
						dateVerification.setQuestion1(str1);
						dateVerification.setQuestion2(str1);
						dateVerification.setShowtime(startTime);
						infraRedService.DataVerification(dateVerification);
					} else if (result.get(0).getData().size() == 0
							&& result1.get(0).getData().size() != 0) {
						dateVerification.setIp1(result.get(0).getIp());
						dateVerification.setIp2(result1.get(0).getIp());
						dateVerification.setQuestion1(str1);
						dateVerification.setQuestion2(str2);
						dateVerification.setShowtime(startTime);
						infraRedService.DataVerification(dateVerification);
					} else if (result.get(0).getData().size() != 0
							&& result1.get(0).getData().size() == 0) {
						dateVerification.setIp1(result.get(0).getIp());
						dateVerification.setIp2(result1.get(0).getIp());
						dateVerification.setQuestion1(str2);
						dateVerification.setQuestion2(str1);
						dateVerification.setShowtime(startTime);
						infraRedService.DataVerification(dateVerification);
					}
					return;
				}
			}
		}
	}*/

}
