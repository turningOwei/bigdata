package framework.jetty;

import java.util.Map;
import java.util.concurrent.Executors;

import framework.executor.ExpiredTaskExecutor;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.util.HttpCookieStore;


/**
 * 基于jetty client的工具类
 * 
 * @author zhaowei
 *
 */
public class HttpClientTool {

	private HttpClient defaultHttpClient;

	public HttpClientTool() throws Exception {
		initDefaultHttpClient();
	}

	private void initDefaultHttpClient() throws Exception {
		defaultHttpClient = new HttpClient();
		defaultHttpClient.setTCPNoDelay(true);
		defaultHttpClient.setMaxConnectionsPerDestination(512);// 太大会把系统tcp连接占光
		defaultHttpClient
				.setMaxRequestsQueuedPerDestination(Integer.MAX_VALUE >>> 4);
		defaultHttpClient.setCookieStore(new HttpCookieStore.Empty());
		defaultHttpClient.setFollowRedirects(false);
		defaultHttpClient.setExecutor(new ExpiredTaskExecutor(Executors
				.newCachedThreadPool(), 10, task -> task.getClass().toString()
				.contains("ReadCallback")));
		defaultHttpClient.start();
	}

	/**
	 * 发起同步 http get 请求，返回响应的文本内容。
	 * 
	 * @param url
	 *            请求地址，包含请求参数。
	 * 
	 * @param charset
	 *            字符编码
	 * @return 响应的文本内容
	 * @throws Throwable
	 */
	public String get(String url, String charset) throws Throwable {
		Request request = defaultHttpClient.newRequest(url);
		ContentResponse response;
		response = request.send();
		return new String(response.getContent(), charset);
	}

	/**
	 * 发起同步 http get 请求，返回响应的文本内容。UTF-8编码。
	 * 
	 * @param url
	 *            请求地址，包含请求参数。
	 * 
	 * @return 响应的文本内容
	 * @throws Throwable
	 */
	public String get(String url) throws Throwable {
		return get(url, "UTF-8");
	}

	public void post(String url, Map<String, String> body,
			HttpResponseContentProcessor processor) throws Throwable {
		defaultHttpClient.POST("url").send(new BufferingResponseListener() {


			public void onComplete(Result result) {
				try {
					if (result.getResponse().getStatus() == 200) {
						String responseStr = new String(getContent(), "UTF-8");
						processor.processContent(responseStr);
					} else {
						processor.processError(new Exception("http失败，返回状态码："
								+ result.getResponse().getStatus()));
					}
				} catch (Throwable e) {
					processor.processError(e);
				}
			}

		});
	}

}
