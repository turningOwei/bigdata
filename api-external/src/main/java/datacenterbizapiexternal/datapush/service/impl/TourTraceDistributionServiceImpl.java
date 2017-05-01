package datacenterbizapiexternal.datapush.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import datacenterbizapiexternal.datapush.dao.mongodb.repositories.TourTraceDistributionRepository;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistribution;
import datacenterbizapiexternal.datapush.domain.entity.TourTraceDistributionEntity;
import datacenterbizapiexternal.datapush.service.TourTraceDistributionService;
import framework.util.CollectionUtil;
import org.springframework.stereotype.Service;

@Service
public class TourTraceDistributionServiceImpl implements
		TourTraceDistributionService {
	private Logger log = LoggerFactory
			.getLogger(TourTraceDistributionServiceImpl.class);

	@Autowired
	private TourTraceDistributionRepository repository;
	@Autowired
	private MongoTemplate mongoTemplate;


	public void addEntity(
			TourTraceDistributionEntity<TourTraceDistribution> tourTraceDistribution) {
		repository.insert(tourTraceDistribution);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })

	public List<TourTraceDistributionEntity<TourTraceDistribution>> queryEntityByStartTimeAndEndTime(
			String startTime, String endTime) {

		List<? extends TourTraceDistributionEntity> list = mongoTemplate.find(
				query(where("time").gte(startTime).lte(endTime)),
				new TourTraceDistributionEntity<TourTraceDistribution>()
						.getClass());

		return (List<TourTraceDistributionEntity<TourTraceDistribution>>) list;
	}


	public File getTraceExcel(String title, List<Map> list) throws IOException {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();

		if (CollectionUtil.isEmpty(list)) {
			return File.createTempFile(title, "xls");
		}
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
		titleCell.setCellStyle(titleStyle);
		// 第一行单元格合并
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, 2);
		sheet.addMergedRegion(range);

		// 创建表头
		HSSFRow subtitleRow = sheet.createRow(1);
		HSSFCell subtitleCell1 = subtitleRow.createCell(0);
		subtitleCell1.setCellValue(title);
		subtitleCell1.setCellStyle(titleStyle);
		subtitleCell1.setCellValue("地区");

		HSSFCell subtitleCell2 = subtitleRow.createCell(1);
		subtitleCell2.setCellValue(title);
		subtitleCell2.setCellStyle(titleStyle);
		subtitleCell2.setCellValue("人数");

		HSSFCell subtitleCell3 = subtitleRow.createCell(2);
		subtitleCell3.setCellValue(title);
		subtitleCell3.setCellStyle(titleStyle);
		subtitleCell3.setCellValue("占比");

		// 添加合计
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			total += (Integer) list.get(i).get("value");
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 2);

			HSSFCell cel1 = row.createCell(0);
			cel1.setCellStyle(cellStyle);

			HSSFCell cel2 = row.createCell(1);
			cel2.setCellStyle(cellStyle);
			HSSFCell cel3 = row.createCell(2);
			cel3.setCellStyle(cellStyle);

			Map map = list.get(i);

			cel1.setCellValue(map.get("name").toString());
			cel2.setCellValue(map.get("value").toString());
			cel3.setCellValue(String.format(
					"%.2f",
					((Integer.valueOf(map.get("value").toString())).intValue() * 100.0 / total))
					+ "%");

		}

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
