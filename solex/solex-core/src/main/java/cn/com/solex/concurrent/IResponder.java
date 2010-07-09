package cn.com.solex.concurrent;

/**
 * 异步回调监听的接口
 * 
 * @author l.xue.nong
 * 
 */
public interface IResponder {

	/**
	 * 处理成功
	 * 
	 * @param <T>
	 * @param result
	 */
	public <T> void onResult(T result);

	/**
	 * 处理失败
	 * 
	 * @param fault
	 */
	public void onFault(Exception fault);
}
