package cn.com.solex.concurrent;

public interface IResponder {
	public <T> void onResult(T result);

	public void onFault(Exception fault);
}
