package cn.com.solex.utils;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * 创建Jetty Server的工具类.
 * 
 * @author XiaoLu
 */
public class JettyUtils {

	/**
	 * 创建用于Debug的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildDebugServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		webContext.setDefaultsDescriptor("webdefault.xml");
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}

	/**
	 * 创建用于Functional TestJetty Server, 以src/main/webapp为Web应用目录.
	 * 以test/resources/web.xml指向applicationContext-test.xml创建测试环境.
	 */
	public static Server buildTestServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		webContext.setDescriptor("src/test/resources/web.xml");
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}
}
