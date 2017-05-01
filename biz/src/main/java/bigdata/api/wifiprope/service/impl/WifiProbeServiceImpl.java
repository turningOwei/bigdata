package bigdata.api.wifiprope.service.impl;

import java.util.ArrayList;
import java.util.List;

import bigdata.api.wifiprope.WifiProbeClient;
import bigdata.api.wifiprope.domain.*;
import bigdata.api.wifiprope.service.WifiProbeService;
import org.springframework.stereotype.Service;


@Service
public class WifiProbeServiceImpl implements WifiProbeService {

	public List<UVInfo> getUVInfo() {
		List<UVInfo> result = new ArrayList<UVInfo>();
		WifiProbeResponse response = WifiProbeClient.getWifiProbe();
		if (response != null && response.getData() != null
				&& response.getData().size() > 0)
			result = response.getData();
		return result;
	}


	public List<UVInfo> getUVInfoByRealTimeInfo() {
		List<UVInfo> resList = new ArrayList<UVInfo>();
		
		ScenicSpot[] scenicSpots = ScenicSpot.values();
		for (ScenicSpot scenicSpot : scenicSpots) {
			UVInfo uvInfo = getUVInfoByScenicSpot(scenicSpot);
			resList.add(uvInfo);
		}
		
		return resList;
	}
	

	public List<RealFlow> getRealTimeNum() {
		List<RealFlow> resList = new ArrayList<RealFlow>();
		
		ScenicSpot[] scenicSpots = ScenicSpot.values();
		for (ScenicSpot scenicSpot : scenicSpots) {
			RealFlow el = getRealFlowByScenicSpot(scenicSpot);
			resList.add(el);
		}
		return resList;
	}

	private UVInfo getUVInfoByScenicSpot(ScenicSpot scenicSpot){
		UVInfo info = new UVInfo();
		info.setName(scenicSpot.getName());
		WifiProbeRealTimeInfoResponse result = WifiProbeClient.getWifiProbeRealTimeInf(scenicSpot.getProbeId());
		List<RealtimeInfo> list = result.getJsondata();
		if(list != null && list.size() > 0){
			info.setUv(list.get(list.size()-1).getTotalVisitor().toString());
		}else{
			info.setMsg("景点id:"+scenicSpot.getProbeId()+"查询数据为空");
		}
		return info;
	}
	
	private RealFlow getRealFlowByScenicSpot(ScenicSpot scenicSpot){
		RealFlow info = new RealFlow();
		info.setName(scenicSpot.getName());
		WifiProbeRealTimeInfoResponse result = WifiProbeClient.getWifiProbeRealTimeInf(scenicSpot.getProbeId());
		List<RealtimeInfo> list = result.getJsondata();
		if(list != null && list.size() > 0){
			info.setNum(list.get(list.size()-1).getCurrVisitor().toString());
		}else{
			info.setMsg("景点id:"+scenicSpot.getProbeId()+"查询数据为空");
		}
		return info;
	}
	
}
