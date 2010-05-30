package cn.com.solex.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.com.solex.converter.ConvertRegisterHelper;
import cn.com.solex.service.BaseService;
import cn.com.solex.utils.BeanUtils;
import cn.com.solex.utils.ServletUtils;


/**
 * 基础Controller
 * 
 * @author xuenong_li
 * 
 */
public class BaseController extends MultiActionController {

	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		// 注册converters
		ConvertRegisterHelper.registerConverters();

	}

	@Autowired
	protected BaseService baseService;

	public void copyProperties(Object target, Object source) {
		BeanUtils.copyProperties(target, source);
	}

	public <T> T copyProperties(Class<T> destClass, Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}

	/**
	 * 初始化binder的回调函数.
	 * 
	 * @see MultiActionController#createBinder(HttpServletRequest,Object)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(
				Short.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(
				Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(
				BigDecimal.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(
				BigInteger.class, true));
	}

	/**
	 * 保存消息在request中,messages.jsp会取出来显示此消息
	 */
	@SuppressWarnings("unchecked")
	protected static void saveMessage(HttpServletRequest request, String message) {
		if (StringUtils.isNotBlank(message)) {
			List list = getOrCreateRequestAttribute(request, "springMessages",
					ArrayList.class);
			list.add(message);
		}
	}

	/**
	 * 保存错误消息在request中,messages.jsp会取出来显示此消息
	 */
	@SuppressWarnings("unchecked")
	protected static void saveError(HttpServletRequest request, String errorMsg) {
		if (StringUtils.isNotBlank(errorMsg)) {
			List list = getOrCreateRequestAttribute(request, "springErrors",
					ArrayList.class);
			list.add(errorMsg);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getOrCreateRequestAttribute(HttpServletRequest request,
			String key, Class<T> clazz) {
		Object value = request.getAttribute(key);
		if (value == null) {
			try {
				value = clazz.newInstance();
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
			request.setAttribute(key, value);
		}
		return (T) value;
	}

	/**
	 * 取得项目绝对路径
	 * 
	 * @param request
	 * @return
	 */
	public String getServerPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content,
			HttpServletResponse response, final String... headers) {
		response = initResponseHeader(contentType, response, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final HttpServletResponse response,
			final String text, final String... headers) {
		render(ServletUtils.TEXT_TYPE, text, response, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final HttpServletResponse response,
			final String html, final String... headers) {
		render(ServletUtils.HTML_TYPE, html, response, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final HttpServletResponse response,
			final String xml, final String... headers) {
		render(ServletUtils.XML_TYPE, xml, response, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response,
			final String jsonString, final String... headers) {
		render(ServletUtils.JSON_TYPE, jsonString, response, headers);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response,
			final Object data, final String... headers) {
		response = initResponseHeader(ServletUtils.JSON_TYPE, response, headers);
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName
	 *            callback函数名.
	 * @param object
	 *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonp(final HttpServletResponse response,
			final String callbackName, final Object object,
			final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		String result = new StringBuilder().append(callbackName).append("(")
				.append(jsonString).append(");").toString();

		// 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
		// 如callback197("{html:'Hello World!!!'}");
		render(ServletUtils.JS_TYPE, result, response, headers);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(
			final String contentType, final HttpServletResponse response,
			final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName
						+ "不是一个合法的header类型");
			}
		}

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setNoCacheHeader(response);
		}

		return response;
	}

}
