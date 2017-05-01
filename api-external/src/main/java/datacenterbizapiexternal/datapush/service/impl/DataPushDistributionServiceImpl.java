package datacenterbizapiexternal.datapush.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import datacenterbizapiexternal.datapush.DataPushClient;
import datacenterbizapiexternal.datapush.dao.mongodb.repositories.DataPushRepository;
import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.domain.entity.RealTimeTouristNumber;
import datacenterbizapiexternal.datapush.service.DataPushDistributionService;
import datacenterbizapiexternal.datapush.service.impl.sort.DataPushSortUtils;
import framework.util.CollectionUtil;
import framework.util.StringUtil;
import org.springframework.stereotype.Service;

@Service
public class DataPushDistributionServiceImpl<T> implements
		DataPushDistributionService<T> {
	private Logger log = LoggerFactory
			.getLogger(DataPushDistributionServiceImpl.class);

	@Autowired
	private DataPushRepository repository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public void addDataPushEntity(DataPushEntity<T> entity) {
		repository.insert(entity);
	}

	public void addDataPushEntities(List<DataPushEntity<T>> entities) {
		repository.insert(entities);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataPushEntity queryEntityByTimeAndType(String time, String type) {
		DataPushEntity entity = repository.findOneByTimeAndType(time, type);
		if (entity != null) {
			entity = DataPushSortUtils.sort(entity, type);
			// 需要计算人数的D
			List<String> list = getComputType();
			if (list.contains(type)) {
				entity = computeEntity(entity, time, type);
			}
		}
		return entity;
	}

	private List<String> getComputType() {
		List<String> list = new ArrayList<String>();
		list.add(DataPushExternalEnum.AGE_DISTRIBUTION.getType());
		list.add(DataPushExternalEnum.ORIGIN_CITY_DISTRIBUTION.getType());
		list.add(DataPushExternalEnum.ORIGIN_PROVINCE_DISTRIBUTION.getType());
		list.add(DataPushExternalEnum.CONSUME_DISTRIBUTION.getType());
		list.add(DataPushExternalEnum.GENDER_DISTRIBUTION.getType());
		list.add(DataPushExternalEnum.TOURIST_TOTAL.getType());
		return list;
	}

	private DataPushEntity<DataPushDistribution> computeEntity(
			DataPushEntity<DataPushDistribution> entity, String time,
			String type) {
		Map<String, String> map = getMap();
		String externalEnum = map.get(type);
		if (!StringUtil.isEmpty(externalEnum)) {
			List<DataPushDistribution> list = entity.getList();
			BigDecimal total = new BigDecimal(getTotal(time, type));
			for (DataPushDistribution dataPushDistribution : list) {
				BigDecimal value = new BigDecimal(
						dataPushDistribution.getValue());
				BigDecimal num = total.multiply(value);
				dataPushDistribution.setKeepValue(dataPushDistribution
						.getValue());
				dataPushDistribution.setNumber(num.intValue());
				dataPushDistribution.setValue(String.valueOf(num.intValue()));
			}
		}
		return entity;
	}

	private Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		DataPushExternalEnum[] values = DataPushExternalEnum.values();
		for (DataPushExternalEnum dataPushExternalEnum : values) {
			map.put(dataPushExternalEnum.getType(),
					dataPushExternalEnum.getType());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private int getTotal(String time, String type) {

		DataPushEntity<DataPushDistribution> total = repository
				.findOneByTimeAndType(time,
						DataPushExternalEnum.TOURIST_TOTAL.getType());
		List<DataPushDistribution> list = total == null ? null : total
				.getList();
		if (CollectionUtil.isEmpty(list))
			return 0;
		String value = list.get(0).getValue();
		if (StringUtil.isEmpty(value))
			return 0;
		return Integer.parseInt(value);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DataPushEntity> touristTotalDistrubition(String startTime,
			String endTime) {
		List<? extends DataPushEntity> list = mongoTemplate.find(
				query(where("time").gte(startTime).lte(endTime).and("type")
						.is(DataPushExternalEnum.TOURIST_TOTAL.getType())),
				new DataPushEntity<DataPushDistribution>().getClass());
		return (List<DataPushEntity>) list;
	}

	public RealTimeTouristNumber realTimeTouristNumber() {
		RealTimeTouristNumber entity = DataPushClient
				.getRealTimeTouristNumber();
		return entity;
	}

	/**
	 * 需要改
	 */
	public File getExcel(List<Map<String, String>> consume,
			List<Map<String, String>> age, List<Map<String, String>> gender,
			String title, String date) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建Excel的工作sheet,对于到一个excel的tab
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setColumnWidth(0, 3500);
		sheet.setColumnWidth(1, 3500);

		// 设置字体样式
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");

		// 给Excel的标题设置样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFont(font);
		titleStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setWrapText(true);

		// 给普通表格设置样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setWrapText(true);

		// 创建第一行
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(title);
		titleCell.setCellStyle(cellStyle);
		// 第一行单元格合并
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, 3);
		sheet.addMergedRegion(range);

		// 创建消费栏
		HSSFRow consumeRow = sheet.createRow(1);
		HSSFCell consumeCell = consumeRow.createCell(0);
		consumeCell.setCellStyle(cellStyle);
		// 合并
		range = new CellRangeAddress(1, 3, 0, 0);
		sheet.addMergedRegion(range);

		int i = 0;
		int totalConsume = 0, totalAge = 0, totalGender = 0;
		// 计算消费总人数
		for (Map<String, String> map : consume) {
			totalConsume += Integer.valueOf(map.get("value"));
		}
		for (Map<String, String> map : consume) {
			HSSFRow row = sheet.createRow(1 + i);
			HSSFCell keyCell = row.createCell(1);
			keyCell.setCellStyle(cellStyle);
			HSSFCell valueCell = row.createCell(2);
			valueCell.setCellStyle(cellStyle);
			HSSFCell valuePercentCell = row.createCell(3);
			valuePercentCell.setCellStyle(cellStyle);
			keyCell.setCellValue(map.get("name"));
			valueCell.setCellValue(map.get("value"));
			valuePercentCell.setCellValue(String.format("%.2f",
					(Integer.valueOf(map.get("value")) * 100.0 / totalConsume))
					+ "%");
			i++;
		}
		consumeCell.setCellValue(date + "游客收入水平");

		// 创建性别栏
		HSSFRow genderRow = sheet.createRow(4);
		HSSFCell genderCell = genderRow.createCell(0);
		genderCell.setCellStyle(cellStyle);
		// 合并
		range = new CellRangeAddress(4, 5, 0, 0);
		sheet.addMergedRegion(range);

		for (Map<String, String> map : gender) {
			HSSFRow row = sheet.createRow(1 + i);
			HSSFCell keyCell = row.createCell(1);
			keyCell.setCellStyle(cellStyle);
			HSSFCell valueCell = row.createCell(2);
			valueCell.setCellStyle(cellStyle);
			HSSFCell valuePercentCell = row.createCell(3);
			valuePercentCell.setCellStyle(cellStyle);
			keyCell.setCellValue(map.get("name"));
			valueCell.setCellValue(map.get("value"));
			valuePercentCell.setCellValue(String.format("%.2f",
					(Integer.valueOf(map.get("value")) * 100.0 / totalConsume))
					+ "%");
			i++;
		}
		genderCell.setCellValue(date + "游客性别比例");

		// 创建年龄栏
		HSSFRow ageRow = sheet.createRow(6);
		HSSFCell ageCell = ageRow.createCell(0);
		ageCell.setCellStyle(cellStyle);
		// 合并
		range = new CellRangeAddress(6, 11, 0, 0);
		sheet.addMergedRegion(range);

		// 加数据之前先做下排序
		age.sort(new Comparator<Map<String, String>>() {

			public int compare(Map<String, String> o1, Map<String, String> o2) {
				// TODO Auto-generated method stub

				return o1.get("name").compareToIgnoreCase(o2.get("name"));
			}

		});

		for (Map<String, String> map : age) {
			HSSFRow row = sheet.createRow(1 + i);
			HSSFCell keyCell = row.createCell(1);
			keyCell.setCellStyle(cellStyle);
			HSSFCell valueCell = row.createCell(2);
			valueCell.setCellStyle(cellStyle);
			HSSFCell valuePercentCell = row.createCell(3);
			valuePercentCell.setCellStyle(cellStyle);
			keyCell.setCellValue(map.get("name"));
			valueCell.setCellValue(map.get("value"));
			valuePercentCell.setCellValue(String.format("%.2f",
					(Integer.valueOf(map.get("value")) * 100.0 / totalConsume))
					+ "%");
			i++;
		}
		ageCell.setCellValue(date + "游客年龄分布");
		FileOutputStream os = null;
		try {
			File excel = File.createTempFile(title, "xls");
			os = new FileOutputStream(excel);
			wb.write(os);
			return excel;
		} catch (IOException e) {
			log.error("生成临时excel出错", e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				log.error("临时excelFileOutputStream关闭错误", e);
			}
		}
		return null;
	}
}
