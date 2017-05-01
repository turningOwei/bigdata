package pub.http;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import pub.common.domain.Code;


public class HttpObject {
	private static Logger log = Logger.getLogger(HttpObject.class);

	public static String getJsonByGet(String urlStr) {

		StringBuffer strBuf = new StringBuffer(128);
		BufferedReader reader = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), Code.UTF_8));
			String line = null;
			while ((line = reader.readLine()) != null)
				strBuf.append(line + " ");

		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				// return "";
			}
			log.error(e.getMessage());
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
		}

		return strBuf.toString();
	}

	/**
	 * 
	 * @Title: getJsonByPost
	 * @Description: TODO()
	 * @param @param urlStr 请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param @param param
	 * @param @return json
	 * @return String 返回类型
	 */
	public static String getJsonByPost(String urlStr, String param) {
		PrintWriter out = null;
		BufferedReader reader = null;
		StringBuffer strBuf = new StringBuffer(128);
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), Code.UTF_8));
			String line = null;
			while ((line = reader.readLine()) != null)
				strBuf.append(line + " ");
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return strBuf.toString();
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String getXml() {
		return "";
	}

	// public static void main(String[] arg) {
	//
	// try {
	// String baiduUrl = "http://api.map.baidu.com/telematics/v3/weather";
	//
	// String location = URLEncoder.encode("绵竹", Code.UTF_8);
	// String params = "location=" + location
	// + "&output=json&ak=xeWG7C59sWEZwWk4XPBOZsgB";
	// String json1 = HttpObject.getJsonByPost(baiduUrl, params);
	//
	// String weixinUrl = "https://api.weixin.qq.com/datacube/getusersummary";
	// String weixinparams =
	// "access_token=&begin_date='2015-12-12'&end_date='2016-01-01'";
	// String jsonweixi = HttpObject
	// .getJsonByPost(weixinUrl, weixinparams);
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// }
}
