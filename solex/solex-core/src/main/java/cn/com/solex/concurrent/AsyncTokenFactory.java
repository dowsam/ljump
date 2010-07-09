package cn.com.solex.concurrent;

public interface AsyncTokenFactory {
	public <T> AsyncToken<T> newToken();
}
