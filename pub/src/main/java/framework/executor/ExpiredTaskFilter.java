package framework.executor;

/**
 * 过滤哪些任务是需要超时控制的。
 * 
 * @author zhaowei
 *
 */
public interface ExpiredTaskFilter {
	boolean isExpiredTask(Runnable task);
}
