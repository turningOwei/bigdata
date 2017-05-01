package framework.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IOUtils {
	public static ZipOutputStream zipFile(File file, String fileName,
			ZipOutputStream zos) {
		try {
			FileInputStream fi = new FileInputStream(file);
			BufferedInputStream bins = new BufferedInputStream(fi, 512);
			// org.apache.tools.zip.ZipEntry 也行
			ZipEntry entry = new ZipEntry(fileName);
			zos.putNextEntry(entry);
			// 向压缩文件中输出数据
			int nNumber;
			byte[] buffer = new byte[512];
			while ((nNumber = bins.read(buffer)) != -1) {
				zos.write(buffer, 0, nNumber);
			}
			// 关闭创建的流对象
			bins.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zos;
	}

}
