package cn.com.solex.utils;



import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Web集成测试工具类.
 * 
 * 1.Spring WebApplicationContext初始化到ServletContext.
 * 2.将MockRequest/Response放入Struts2的ServletActionContext.
 * 
 * @author XiaoLu
 */
public class WebTestUtils {


	/**
	 * 在ServletContext里初始化Spring WebApplicationContext.
	 * 
	 * @param configLocations
	 *            application context路径列表.
	 */
	public static void initWebApplicationContext(
			MockServletContext servletContext, String... configLocations) {
		String configLocationsString = StringUtils.join(configLocations, ",");
		servletContext.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				configLocationsString);
		new ContextLoader().initWebApplicationContext(servletContext);
	}

	/**
	 * 在ServletContext里初始化Spring WebApplicationContext.
	 * 
	 * @param applicationContext
	 *            已创建的ApplicationContext.
	 */
	public static void initWebApplicationContext(
			MockServletContext servletContext,
			ApplicationContext applicationContext) {
		ConfigurableWebApplicationContext wac = new XmlWebApplicationContext();
		wac.setParent(applicationContext);
		wac.setServletContext(servletContext);
		wac.setConfigLocation("");
		servletContext.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				wac);
		wac.refresh();
	}

	/**
	 * 关闭ServletContext中的Spring WebApplicationContext.
	 */
	public static void closeWebApplicationContext(
			MockServletContext servletContext) {
		new ContextLoader().closeWebApplicationContext(servletContext);
	}

}
