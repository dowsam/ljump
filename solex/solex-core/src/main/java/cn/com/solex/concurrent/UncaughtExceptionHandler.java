package cn.com.solex.concurrent;

/**
 * 异步回调接口,处理异常接口
 * 
 * @author Administrator
 * 
 */
public interface UncaughtExceptionHandler {
	/**
	 * 处理异常
	 * 
	 * @param responder
	 * @param e
	 * @see cn.com.solex.concurrent.IResponder
	 */
	void uncaughtException(IResponder responder, Throwable e);
}
