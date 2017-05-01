package datacenterbizapiexternal.wechat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import datacenterbizapiexternal.wechat.domain.AccessTokenObj;
import datacenterbizapiexternal.wechat.domain.AccessTokenUrlEnum;
import datacenterbizapiexternal.wechat.domain.WechatCumulateUserInfoResponseParams;
import datacenterbizapiexternal.wechat.domain.WechatUserInfoResponseParams;
import framework.util.StringUtil;
import pub.http.HttpObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WechatClient {

	static String getusersummaryUrlFormat = "https://api.weixin.qq.com/datacube/getusersummary?access_token=%s";
	static String getusercumulateUrlFormat = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=%s";
	static String getusersummaryUrlParamFormat = "{\"begin_date\": \"%s\",\"end_date\": \"%s\"}";
	static String getusercumulateUrlParamFormat = getusersummaryUrlParamFormat;
	static String xcdfsAccessTokoenUrl = "http://ds.zjxcdfs.com/fruits-service/payConfig/getXcdfsToken";

	public static AccessTokenObj getXcdfsAccessToken() {
		// return getAccessToken(AccessTokenUrlEnum.XCDFS_ACCESS_TOKEN);
		Gson gson = new Gson();
		HttpObject ho = new HttpObject();
		String getResult = ho.getJsonByGet(xcdfsAccessTokoenUrl);
		AccessTokenObj result = gson.fromJson(getResult,
				new TypeToken<AccessTokenObj>() {
				}.getType());
		return result;
	}

	/**
	 * @Title: getXclyAccessToken
	 * @Description: 新昌旅游
	 * @param @return 设定文件
	 * @return AccessTokenObj 返回类型
	 */
	public static AccessTokenObj getXclyAccessToken() {
		return getAccessToken(AccessTokenUrlEnum.XCLY_ACCESS_TOKEN);
	}

	private static AccessTokenObj getAccessToken(AccessTokenUrlEnum tokenEnum) {
		HttpObject ho = new HttpObject();
		Gson gson = new Gson();
		String formamter = tokenEnum.urlFormatter;
		String uri = tokenEnum.getUri();
		String paramFormatter = tokenEnum.getParamsFormatter();
		String params = String.format(paramFormatter,
				tokenEnum.getGrant_type(), tokenEnum.getAppID(),
				tokenEnum.getAppSecret());

		String url = String.format(formamter, uri, params);
		String getResult = ho.getJsonByGet(url);
		AccessTokenObj result = gson.fromJson(getResult,
				new TypeToken<AccessTokenObj>() {
				}.getType());
		return result;
	}

	@SuppressWarnings("deprecation")
	public static WechatUserInfoResponseParams getUserInfo(String accessToken,
			String startDate, String endDate) throws ClientProtocolException,
			IOException {
		String url = String.format(getusersummaryUrlFormat, accessToken);
		String param = String.format(getusersummaryUrlParamFormat, startDate,
				endDate);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity postingString = new StringEntity(param);// json传递
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(post);
		String content = EntityUtils.toString(response.getEntity());
		WechatUserInfoResponseParams params = new WechatUserInfoResponseParams();
		if (!StringUtil.isEmpty(content))
			params = new Gson().fromJson(content,
					new TypeToken<WechatUserInfoResponseParams>() {
					}.getType());
		return params;
	}

	/**
	 * @Title: getUserCumulate
	 * @Description: 用户每天总量
	 * @param @param accessToken
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException 设定文件
	 * @return WechatCumulateUserInfoResponseParams 返回类型
	 */
	public static WechatCumulateUserInfoResponseParams getUserCumulate(
			String accessToken, String startDate, String endDate)
			throws ClientProtocolException, IOException {
		String url = String.format(getusercumulateUrlFormat, accessToken);
		String param = String.format(getusercumulateUrlParamFormat, startDate,
				endDate);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity postingString = new StringEntity(param);// json传递
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(post);
		String content = EntityUtils.toString(response.getEntity());
		WechatCumulateUserInfoResponseParams params = new WechatCumulateUserInfoResponseParams();
		if (!StringUtil.isEmpty(content))
			params = new Gson().fromJson(content,
					new TypeToken<WechatCumulateUserInfoResponseParams>() {
					}.getType());
		return params;
	}

}
