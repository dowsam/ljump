package cn.com.solex.concurrent;

/**
 * 异步监听,工厂类,具体生产监听器
 * 
 * @author l.xue.nong
 * 
 */
public interface AsyncTokenFactory {
	/**
	 * 生产监听器
	 * 
	 * @param <T>
	 * @return
	 */
	public <T> AsyncToken<T> newToken();
}
