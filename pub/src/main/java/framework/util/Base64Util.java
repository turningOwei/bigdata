package framework.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * @ClassName: Base64Util
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhaowei 716517@qq.com
 * @date 2015年12月17日 下午2:51:31
 * 
 */
public class Base64Util {
	/**
	 * 将二进制数据编码为BASE64字符串
	 * 
	 * @param binaryData
	 * @return
	 */
	public static String encode(String binaryData) {
		try {
			return new String(Base64.encodeBase64(binaryData.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 将BASE64字符串恢复为二进制数据
	 * 
	 * @param base64String
	 * @return
	 */
	public static String decode(String base64String) {
		try {
			return new String(Base64.decodeBase64(base64String
					.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
