package cn.com.solex.ljump.tools;

import org.mortbay.jetty.Server;

import cn.com.solex.utils.JettyUtils;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车停止服务.
 * 
 * @author Administrator
 * 
 */
public class Start {
	public static final int PORT = 8080;
	public static final String CONTEXT = "/solex-ljump";
	public static final String BASE_URL = "http://localhost:8080/solex-ljump";

	public static void main(String[] args) throws Exception {
		Server server = JettyUtils.buildDebugServer(PORT, CONTEXT);
		server.start();
		

		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
	}
}
