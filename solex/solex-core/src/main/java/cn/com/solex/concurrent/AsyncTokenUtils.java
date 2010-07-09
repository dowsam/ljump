package cn.com.solex.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public class AsyncTokenUtils {
	/**
	 * 通过executor执行Callable,将callable的执行结果设置进token
	 */
	public static <T> void execute(Executor executor,AsyncToken<T> token,final Callable<T> task) {
		executor.execute(new AsyncTokenRunnable<T>(token,task));
	}
	/**
	 * 通过executor执行Runnable
	 */
	public static <T> void execute(Executor executor,AsyncToken<T> token,final Runnable task) {
		executor.execute(new AsyncTokenRunnable<T>(token,task));
	}
	
	/**
	 * 通过executor执行Callable,将callable的执行结果设置进token
	 */
	public static <T> AsyncToken<T> execute(Executor executor,AsyncTokenFactory factory,final Callable<T> task) {
		AsyncToken<T> token = factory.newToken();
		executor.execute(new AsyncTokenRunnable<T>(token,task));
		return token;
	}
	/**
	 * 通过executor执行Runnable
	 */
	public static <T> AsyncToken<T> execute(Executor executor,AsyncTokenFactory factory,final Runnable task) {
		AsyncToken<T> token = factory.newToken();
		executor.execute(new AsyncTokenRunnable<T>(token,task));
		return token;
	}
}
