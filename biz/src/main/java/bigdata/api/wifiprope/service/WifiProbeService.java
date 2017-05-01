package bigdata.api.wifiprope.service;

import bigdata.api.wifiprope.domain.RealFlow;
import bigdata.api.wifiprope.domain.UVInfo;

import java.util.List;


public interface WifiProbeService {
	@Deprecated
	public List<UVInfo> getUVInfo();
	
	public List<UVInfo> getUVInfoByRealTimeInfo();
	
	public List<RealFlow> getRealTimeNum();
}
