package datacenterbizapiexternal.datapush.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import  datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import  datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import  datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import  datacenterbizapiexternal.datapush.service.DataPushExcelService;
import  framework.util.CollectionUtil;
import org.springframework.stereotype.Service;

@Service
public class DataPushExcelServiceImpl implements DataPushExcelService {
	private Logger log = LoggerFactory
			.getLogger(DataPushExcelServiceImpl.class);

	@SuppressWarnings("resource")
	public File getExcel(DataPushEntity<DataPushDistribution> entity,
			DataPushExternalEnum externalEnum, String time) throws IOException {
		// 设置标题
		String description = externalEnum.getDescription();
		String name = time + description;
		int sum = 0;
		int count = 0;

		HSSFWorkbook wb = new HSSFWorkbook();

		if (entity == null || CollectionUtil.isEmpty(entity.getList())) {
			return File.createTempFile(name, "xls");
		}
		List<DataPushDistribution> list;
		list = entity.getList();
		// 创建Excel的工作sheet,对于到一个excel的tab
		HSSFSheet sheet = wb.createSheet(time);
		sheet.setColumnWidth(0, 3500);
		sheet.setColumnWidth(1, 3500);

		// 设置字体样式
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 18);

		// 设置表头字体样式
		HSSFFont headFont = wb.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 12);

		// 给Excel的标题设置样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFont(font);
		titleStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setWrapText(true);

		// 给Excel的表头设置样式
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setWrapText(true);

		// 给普通表格设置样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setWrapText(true);

		// 创建第一行
		HSSFRow title = sheet.createRow(0);
		HSSFCell titleCell = title.createCell(0);
		titleCell.setCellValue(name);
		titleCell.setCellStyle(titleStyle);
		// 第一行单元格合并
		CellRangeAddress range = new CellRangeAddress(0, 1, 0, 7);
		sheet.addMergedRegion(range);

		// 创建第二行表头
		HSSFRow row1 = sheet.createRow(2);
		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue(description + "区间");
		cell1.setCellStyle(headStyle);
		range = new CellRangeAddress(2, 2, 0, 1);
		sheet.addMergedRegion(range);

		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellValue("人数");
		cell2.setCellStyle(headStyle);
		range = new CellRangeAddress(2, 2, 2, 3);
		sheet.addMergedRegion(range);

		if (externalEnum.name().equals("ORIGIN_CITY_DISTRIBUTION")
				|| externalEnum.name().equals("ORIGIN_PROVINCE_DISTRIBUTION")) {
			HSSFCell cell3 = row1.createCell(4);
			cell3.setCellValue("占比");
			cell3.setCellStyle(headStyle);
			range = new CellRangeAddress(2, 2, 4, 5);
			sheet.addMergedRegion(range);
		}

		for (int j = 0; j < list.size(); j++) {
			sum += list.get(j).getNumber();
		}
		for (int i = 0; i < list.size(); i++) {
			DataPushDistribution element = list.get(i);

			HSSFRow row = sheet.createRow(i + 3);
			HSSFCell key = row.createCell(0);
			key.setCellStyle(cellStyle);
			range = new CellRangeAddress(i + 3, i + 3, 0, 1);
			sheet.addMergedRegion(range);

			HSSFCell value = row.createCell(2);
			value.setCellStyle(cellStyle);
			range = new CellRangeAddress(i + 3, i + 3, 2, 3);
			sheet.addMergedRegion(range);

			if (externalEnum.name().equals("ORIGIN_CITY_DISTRIBUTION")
					|| externalEnum.name().equals(
							"ORIGIN_PROVINCE_DISTRIBUTION")) {
				HSSFCell cel1 = row.createCell(4);
				cel1.setCellStyle(cellStyle);
				range = new CellRangeAddress(i + 3, i + 3, 4, 5);
				sheet.addMergedRegion(range);

				cel1.setCellValue(String.format("%.2f",
						(element.getNumber() * 100.0) / sum) + "%");
			}

			key.setCellValue(element.getKey());
			value.setCellValue(element.getNumber());
			count += element.getNumber();

		}

		// 创建第二行表头
		HSSFRow rows = sheet.createRow(3 + list.size());
		HSSFCell cells1 = rows.createCell(0);
		cells1.setCellValue("合计");
		cells1.setCellStyle(headStyle);
		range = new CellRangeAddress(3 + list.size(), 3 + list.size(), 0, 1);
		sheet.addMergedRegion(range);

		HSSFCell cells2 = rows.createCell(2);
		cells2.setCellValue(count);
		cells2.setCellStyle(headStyle);
		range = new CellRangeAddress(3 + list.size(), 3 + list.size(), 2, 3);
		sheet.addMergedRegion(range);

		FileOutputStream os = null;
		try {
			File excel = File.createTempFile(name, "xls");
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

	@SuppressWarnings("resource")
	public File getExcel(List<DataPushDistribution> list,
			DataPushExternalEnum externalEnum, String startTime, String endTime)
			throws IOException {
		// 设置标题,历史客流时间会出错
		String description = externalEnum.getDescription();
		String name = startTime + "-" + list.get(list.size() - 1).getKey()
				+ description;
		int count = 0;

		HSSFWorkbook wb = new HSSFWorkbook();

		if (CollectionUtil.isEmpty(list)) {
			return File.createTempFile(name, "xls");
		}
		// 创建Excel的工作sheet,对于到一个excel的tab
		HSSFSheet sheet = wb.createSheet(name);
		sheet.setColumnWidth(0, 3500);
		sheet.setColumnWidth(1, 3500);

		// 设置字体样式
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 18);

		// 设置表头字体样式
		HSSFFont headFont = wb.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 12);

		// 给Excel的标题设置样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFont(font);
		titleStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setWrapText(true);

		// 给Excel的表头设置样式
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setWrapText(true);

		// 给普通表格设置样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setWrapText(true);

		// 创建第一行
		HSSFRow title = sheet.createRow(0);
		HSSFCell titleCell = title.createCell(0);
		titleCell.setCellValue(name);
		titleCell.setCellStyle(titleStyle);
		// 第一行单元格合并
		CellRangeAddress range = new CellRangeAddress(0, 1, 0, 5);
		sheet.addMergedRegion(range);

		// 创建第二行表头
		HSSFRow row1 = sheet.createRow(2);
		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue("时间");
		cell1.setCellStyle(headStyle);
		range = new CellRangeAddress(2, 2, 0, 2);
		sheet.addMergedRegion(range);

		HSSFCell cell2 = row1.createCell(3);
		cell2.setCellValue("人数");
		cell2.setCellStyle(headStyle);
		range = new CellRangeAddress(2, 2, 3, 5);
		sheet.addMergedRegion(range);

		for (int i = 0; i < list.size(); i++) {
			DataPushDistribution element = list.get(i);
			count += Integer.parseInt(element.getValue());
		}

		for (int i = 0; i < list.size(); i++) {

			HSSFRow row = sheet.createRow(i + 3);
			HSSFCell key = row.createCell(0);
			key.setCellStyle(cellStyle);
			range = new CellRangeAddress(i + 3, i + 3, 0, 2);
			sheet.addMergedRegion(range);

			HSSFCell value = row.createCell(3);
			value.setCellStyle(cellStyle);
			range = new CellRangeAddress(i + 3, i + 3, 3, 5);
			sheet.addMergedRegion(range);

			DataPushDistribution element = list.get(i);
			key.setCellValue(element.getKey());
			value.setCellValue(element.getValue());
		}

		// 创建第二行表头
		HSSFRow rows = sheet.createRow(3 + list.size());
		HSSFCell cells1 = rows.createCell(0);
		cells1.setCellValue("合计");
		cells1.setCellStyle(headStyle);
		range = new CellRangeAddress(3 + list.size(), 3 + list.size(), 0, 2);
		sheet.addMergedRegion(range);

		HSSFCell cells2 = rows.createCell(3);
		cells2.setCellValue(count);
		cells2.setCellStyle(headStyle);
		range = new CellRangeAddress(3 + list.size(), 3 + list.size(), 3, 5);
		sheet.addMergedRegion(range);

		FileOutputStream os = null;
		try {
			File excel = File.createTempFile(name, "xls");
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
