package bigdata.api.wifiprope;

import java.util.List;


import bigdata.api.wifiprope.domain.RealtimeInfo;
import bigdata.api.wifiprope.domain.ScenicSpot;
import bigdata.api.wifiprope.domain.WifiProbeRealTimeInfoResponse;
import bigdata.api.wifiprope.domain.WifiProbeResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pub.http.HttpObject;

public class WifiProbeClient {

	static String WIFI_PROBE_URL = "http://139.196.211.76:19080/data/realtimeuv.do?appkey=nt00gNM3raNpmMSW0erh5w";
	static String WIFI_PROBE_REALTIMEINFO_URL = "http://139.196.211.45:8080/api/getRealtimeInfo?probeid=";

	@Deprecated
	public static WifiProbeResponse getWifiProbe() {
		Gson gson = new Gson();
		HttpObject ho = new HttpObject();
		String getResult = ho.getJsonByGet(WIFI_PROBE_URL);
		WifiProbeResponse result = gson.fromJson(getResult, new TypeToken<WifiProbeResponse>() {
		}.getType());
		return result;
	}

	public static WifiProbeRealTimeInfoResponse getWifiProbeRealTimeInf(String probeId) {
		String url = WIFI_PROBE_REALTIMEINFO_URL + probeId;
		Gson gson = new Gson();
		HttpObject ho = new HttpObject();
		String getResult = ho.getJsonByGet(url);
		WifiProbeRealTimeInfoResponse result = gson.fromJson(getResult, new TypeToken<WifiProbeRealTimeInfoResponse>() {
		}.getType());
		return result;
	}

	public static void main(String[] args) {
		WifiProbeRealTimeInfoResponse result = WifiProbeClient.getWifiProbeRealTimeInf(ScenicSpot.FO_XIN_GUANG_CHANG.getProbeId());
		List<RealtimeInfo> list = result.getJsondata();
		System.out.println(list.get(list.size() - 1).getTotalVisitor());
	}
}
