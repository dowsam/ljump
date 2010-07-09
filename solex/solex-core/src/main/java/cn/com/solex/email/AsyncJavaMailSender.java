package cn.com.solex.email;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.Assert;

import cn.com.solex.concurrent.AsyncToken;
import cn.com.solex.concurrent.AsyncTokenFactory;
import cn.com.solex.concurrent.AsyncTokenUtils;
import cn.com.solex.concurrent.DefaultAsyncTokenFactory;

/**
 * 提供异步发送邮件,启动监听模式
 * 
 * @author l.xue.nong
 * 
 */
public class AsyncJavaMailSender implements InitializingBean, DisposableBean,
		BeanNameAware {

	protected static final Logger log = LoggerFactory
			.getLogger(AsyncJavaMailSender.class);
	protected boolean shutdownExecutorService = true;
	private String beanName;
	protected int sendMailThreadPoolSize = 0;
	protected ExecutorService executorService; // 邮件发送的线程池
	protected boolean waitForTasksToCompleteOnShutdown = true;
	protected AsyncTokenFactory asyncTokenFactory = new DefaultAsyncTokenFactory();
	protected MimeMailService mimeMailService;

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@Override
	public void destroy() throws Exception {
		if (shutdownExecutorService) {
			shutdown();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (executorService == null && sendMailThreadPoolSize > 0) {
			executorService = Executors.newFixedThreadPool(
					sendMailThreadPoolSize, new CustomizableThreadFactory(
							getClass().getSimpleName() + "-"));
			log.info("create send mail executorService,sendMailThreadPoolSize:"
					+ sendMailThreadPoolSize);
		}
		Assert.notNull(mimeMailService, "mimeMailService must be not null");
		Assert.notNull(executorService, "executorService must be not null");
		Assert.notNull(asyncTokenFactory, "asyncTokenFactory must be not null");
	}

	public void shutdown() {
		log.info("Shutting down ExecutorService"
				+ (this.beanName != null ? " '" + this.beanName + "'" : ""));
		if (waitForTasksToCompleteOnShutdown)
			executorService.shutdown();
		else
			executorService.shutdownNow();
	}

	public boolean isWaitForTasksToCompleteOnShutdown() {
		return waitForTasksToCompleteOnShutdown;
	}

	public void setWaitForTasksToCompleteOnShutdown(
			boolean waitForTasksToCompleteOnShutdown) {
		this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
	}

	public void setSendMailThreadPoolSize(int sendMailThreadPool) {
		this.sendMailThreadPoolSize = sendMailThreadPool;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public AsyncTokenFactory getAsyncTokenFactory() {
		return asyncTokenFactory;
	}

	public void setAsyncTokenFactory(AsyncTokenFactory asyncTokenFactory) {
		this.asyncTokenFactory = asyncTokenFactory;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public void setShutdownExecutorService(boolean shutdownExecutorService) {
		this.shutdownExecutorService = shutdownExecutorService;
	}

	public boolean isShutdownExecutorService() {
		return shutdownExecutorService;
	}

	public MimeMailService getMimeMailService() {
		return mimeMailService;
	}

	public void setMimeMailService(MimeMailService mimeMailService) {
		this.mimeMailService = mimeMailService;
	}

	public <T> AsyncToken<T> send(final String from, final String to,
			final String subject) {
		return AsyncTokenUtils.execute(executorService, asyncTokenFactory,
				new Runnable() {
					@Override
					public void run() {
						mimeMailService.send(from, to, subject);
					}
				});
	}

	public <T> AsyncToken<T> send(final String from, final String to,
			final String subject, final String templateName,
			final Map<String, Object> map) {
		return AsyncTokenUtils.execute(executorService, asyncTokenFactory,
				new Runnable() {
					@Override
					public void run() {
						mimeMailService.send(from, to, subject, templateName,
								map);
					}
				});
	}

	public <T> AsyncToken<T> send(final String from, final String[] to,
			final String subject, final String templateName,
			final Map<String, Object> map) {
		return AsyncTokenUtils.execute(executorService, asyncTokenFactory,
				new Runnable() {
					@Override
					public void run() {
						mimeMailService.send(from, to, subject, templateName,
								map);
					}
				});
	}

	public <T> AsyncToken<T> send(final String from, final String[] to,
			final String subject, final String templateName,
			final Map<String, Object> map, final String fileName) {
		return AsyncTokenUtils.execute(executorService, asyncTokenFactory,
				new Runnable() {
					@Override
					public void run() {
						mimeMailService.send(from, to, subject, templateName,
								map, fileName);
					}
				});
	}

	public <T> AsyncToken<T> send(final String from, final String to,
			final String subject, final String templateName,
			final Map<String, Object> map, final String fileName) {
		return AsyncTokenUtils.execute(executorService, asyncTokenFactory,
				new Runnable() {
					@Override
					public void run() {
						mimeMailService.send(from, to, subject, templateName,
								map, fileName);
					}
				});
	}
}