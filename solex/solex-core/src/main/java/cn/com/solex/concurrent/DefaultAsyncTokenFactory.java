package cn.com.solex.concurrent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * 默认工厂类,主要生产异步监听器,
 * 
 * @see cn.com.solex.concurrent.IResponder
 * @author L.xue.nong
 * 
 */
public class DefaultAsyncTokenFactory implements AsyncTokenFactory {

	private String tokenGroup = AsyncToken.DEFAULT_TOKEN_GROUP;
	private String tokenName;
	private List<IResponder> responders = new ArrayList<IResponder>();
	private UncaughtExceptionHandler uncaughtExceptionHandler;

	public String getTokenGroup() {
		return tokenGroup;
	}

	public void setTokenGroup(String tokenGroup) {
		this.tokenGroup = tokenGroup;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public List<IResponder> getResponders() {
		return responders;
	}

	public void setResponders(List<IResponder> responders) {
		Assert.notNull(responders, "responders must be not null");
		this.responders = responders;
	}

	public void addResponder(IResponder r) {
		this.responders.add(r);
	}

	public UncaughtExceptionHandler getUncaughtExceptionHandler() {
		return uncaughtExceptionHandler;
	}

	public void setUncaughtExceptionHandler(
			UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
	}

	public <T> AsyncToken<T> newToken() {
		AsyncToken<T> t = new AsyncToken<T>();

		t.setTokenGroup(tokenGroup);
		t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		t.setTokenName(tokenName);

		for (IResponder r : responders) {
			t.addResponder(r);
		}

		return t;
	}

}
