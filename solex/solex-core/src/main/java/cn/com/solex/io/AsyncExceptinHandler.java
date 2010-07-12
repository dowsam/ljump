package cn.com.solex.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理异步IO写入异常处理
 * 
 * @author l.xue.nong
 * 
 */
public interface AsyncExceptinHandler {
	public void handle(Throwable e);

	public static class DefaultAsyncExceptinHandler implements
			AsyncExceptinHandler {
		private static Logger log = LoggerFactory.getLogger(AsyncWriter.class);

		public void handle(Throwable e) {
			log.error("Exception during write(): " + e, e);
		}
	};
}
