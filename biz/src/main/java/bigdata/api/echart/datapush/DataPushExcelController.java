package bigdata.api.echart.datapush;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import datacenterbizapiexternal.datapush.domain.DataPushExternalEnum;
import datacenterbizapiexternal.datapush.domain.entity.DataPushDistribution;
import datacenterbizapiexternal.datapush.domain.entity.DataPushEntity;
import datacenterbizapiexternal.datapush.service.DataPushDistributionService;
import datacenterbizapiexternal.datapush.service.DataPushExcelService;
import framework.util.CollectionUtil;
import framework.util.date.DateStyle;
import framework.util.date.DateUtil;

@Controller
@RequestMapping("/excel/datapush")
public class DataPushExcelController {
	private Logger log = LoggerFactory.getLogger(DataPushExcelController.class);
	@Autowired
	private DataPushDistributionService<DataPushDistribution> dataPushDistributionService;
	@Autowired
	private DataPushExcelService dataPushExcelService;

	@RequestMapping(params = { "method=ageDistribution" })
	@ResponseBody()
	public void ageDistribution(ModelMap model, String time, String enumName,
			HttpServletResponse response) {
		this.normalDistribution(model, time, enumName, response);
	}

	@RequestMapping(params = { "method=genderDistribution" })
	@ResponseBody()
	public void genderDistribution(ModelMap model, String time,
			String enumName, HttpServletResponse response) {
		this.normalDistribution(model, time, enumName, response);
	}

	@RequestMapping(params = { "method=consumeDistribution" })
	@ResponseBody()
	public void consumeDistribution(ModelMap model, String time,
			String enumName, HttpServletResponse response) {
		this.normalDistribution(model, time, enumName, response);
	}

	@RequestMapping(params = { "method=originProvinceDistribution" })
	@ResponseBody()
	public void originProvince(ModelMap model, String time, String enumName,
			HttpServletResponse response) {
		this.normalDistribution(model, time, enumName, response);
	}

	@RequestMapping(params = { "method=originCityDistribution" })
	@ResponseBody()
	public void originCityDistribution(ModelMap model, String time,
			String enumName, HttpServletResponse response) {
		this.normalDistribution(model, time, enumName, response);
	}

	// */

	@SuppressWarnings("unchecked")
	public void normalDistribution(ModelMap model, String time,
			String enumName, HttpServletResponse response) {
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);

		DataPushEntity<DataPushDistribution> entity = dataPushDistributionService
				.queryEntityByTimeAndType(time, dataPushEnum.getType());
		String description = dataPushEnum.getDescription();
		String name = time + description;
		FileInputStream fi = null;
		BufferedInputStream bis = null;
		File excel = null;

		try {
			excel = dataPushExcelService.getExcel(entity, dataPushEnum, time);
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(name, "UTF-8") + ".xls");
			ServletOutputStream out = response.getOutputStream();
			fi = new FileInputStream(excel);
			bis = new BufferedInputStream(fi, 512);
			int nNumber;
			byte[] buffer = new byte[512];
			while ((nNumber = bis.read(buffer)) != -1) {
				out.write(buffer, 0, nNumber);
			}

		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
		} catch (IOException e) {
			log.error("IOException", e);
		} finally {
			if (excel.exists())
				excel.deleteOnExit();
			try {
				fi.close();
				bis.close();
			} catch (IOException e) {
				log.error("excel流或者缓存流关闭出错", e);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(params = { "method=touritstDayTotal" })
	@ResponseBody()
	public void touritstDayTotal(ModelMap model, String startTime,
			String endTime, String enumName, HttpServletResponse response) {
		Date startDate = DateUtil.StringToDate(startTime, DateStyle.YYYY_MM_DD);
		Date endDate = DateUtil.StringToDate(endTime, DateStyle.YYYY_MM_DD);
		startTime = DateUtil.DateToString(startDate, DateStyle.YYYYMMDD);
		endTime = DateUtil.DateToString(endDate, DateStyle.YYYYMMDD);
		String name = "新昌县历史客流量";
		List<DataPushEntity> list = dataPushDistributionService
				.touristTotalDistrubition(startTime, endTime);
		List<DataPushDistribution> innerList = getTouritstDayTotalList(list);
		DataPushExternalEnum dataPushEnum = DataPushExternalEnum
				.valueOf(enumName);
		FileInputStream fi = null;
		BufferedInputStream bis = null;
		File excel = null;
		try {
			excel = dataPushExcelService.getExcel(innerList, dataPushEnum,
					startTime, endTime);
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(name, "UTF-8") + ".xls");
			ServletOutputStream out = response.getOutputStream();
			fi = new FileInputStream(excel);
			bis = new BufferedInputStream(fi, 512);

			int nNumber;
			byte[] buffer = new byte[512];
			while ((nNumber = bis.read(buffer)) != -1) {
				out.write(buffer, 0, nNumber);
			}
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
		} catch (IOException e) {
			log.error("IOException", e);
		} finally {
			if (excel.exists())
				excel.deleteOnExit();
			try {
				fi.close();
				bis.close();
			} catch (IOException e) {
				log.error("excel流或者缓存流关闭出错", e);
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	private List<DataPushDistribution> getTouritstDayTotalList(
			List<DataPushEntity> list) {
		List<DataPushDistribution> innerList = new ArrayList<DataPushDistribution>();
		if (CollectionUtil.isNotEmpty(list)) {
			for (DataPushEntity entity : list) {
				if (CollectionUtil.isNotEmpty(entity.getList())) {
					DataPushDistribution elment = (DataPushDistribution) entity
							.getList().get(0);
					String time = entity.getTime();
					DataPushDistribution nElment = new DataPushDistribution();
					nElment.setKey(time);
					nElment.setValue(elment.getValue());
					innerList.add(nElment);
				}
			}
		}
		return innerList;
	}

}
