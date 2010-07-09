package cn.com.solex.concurrent;

public interface UncaughtExceptionHandler {
	void uncaughtException(IResponder responder, Throwable e);
}
