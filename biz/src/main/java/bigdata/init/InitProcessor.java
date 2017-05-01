package bigdata.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring 容器初始化完成后执行
 * 
 *
 */
public class InitProcessor implements
		ApplicationListener<ContextRefreshedEvent> {


	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getSource().toString().contains("root")) {
			CacheOperater.initCacheDate();
			// SDKConfig.getConfig().loadPropertiesFromSrc();//
			// 从classpath加载acp_sdk.properties文件
		}
	}

}
