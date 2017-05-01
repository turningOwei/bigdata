package bigdata.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

public class NormalDistribution {

	@SuppressWarnings("unchecked")
	public static void normalDistribution(String name, File excel,
			HttpServletResponse response, Logger log) {
		FileInputStream fi = null;
		BufferedInputStream bis = null;
		try {
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

}
