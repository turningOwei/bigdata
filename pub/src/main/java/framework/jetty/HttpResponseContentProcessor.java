package framework.jetty;

/**
 * 返回内容（字符串）处理。用于异步
 * 
 * @author zhaowei
 *
 */
public interface HttpResponseContentProcessor {

	void processContent(String content1);

	void processError(Throwable err);

}
