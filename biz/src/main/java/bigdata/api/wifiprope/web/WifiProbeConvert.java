package bigdata.api.wifiprope.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WifiProbeConvert {

	public static String convertToJsonp(Object result, String callback) {
		StringBuffer sb = new StringBuffer(callback);
		Gson gson = new GsonBuilder().serializeNulls().create();
		String json = gson.toJson(result);
		sb.append("(").append(json).append(")");
		return sb.toString();
	}
}
