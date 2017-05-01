package bigdata.api.wifiprope.web;

import java.util.List;

import bigdata.api.wifiprope.domain.RealFlow;
import bigdata.api.wifiprope.domain.UVInfo;
import bigdata.api.wifiprope.service.WifiProbeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/wifiprobe")
public class WifiProbeCtrl {
	private Logger log = LoggerFactory.getLogger(WifiProbeCtrl.class);

	@Autowired
	private WifiProbeService wifiProbeService;

	@RequestMapping(params = { "method=getUVInfo" })
	@ResponseBody()
	public String getUVInfo(String callback) {
		List<UVInfo> list = wifiProbeService.getUVInfoByRealTimeInfo();
		
		return WifiProbeConvert.convertToJsonp(list, callback);
	}

	@RequestMapping(params = { "method=getRealTimeNum" })
	@ResponseBody()
	public String getRealTimeNum(String callback) {
		List<RealFlow> list = wifiProbeService.getRealTimeNum();
		
		return WifiProbeConvert.convertToJsonp(list, callback);
	}
}
