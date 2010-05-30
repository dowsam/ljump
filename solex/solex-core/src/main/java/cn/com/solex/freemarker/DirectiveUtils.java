package cn.com.solex.freemarker;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
/**
 * freemarker 扩展工具类
 * @author xuenong_li
 *
 */
public class DirectiveUtils {
	public static String BLOCK = "__ftl_override__";

	public static String getOverrideVariableName(String name) {
		return BLOCK + name;
	}

	public static void exposeRapidMacros(Configuration conf) {
		conf.setSharedVariable(new BlockDirective().getName(),
				new BlockDirective());
		conf.setSharedVariable(new ExtendsDirective().getName(),
				new ExtendsDirective());
		conf.setSharedVariable(new OverrideDirective().getName(),
				new OverrideDirective());
	}

	@SuppressWarnings("unchecked")
	static String getRequiredParam(Map params, String key)
			throws TemplateException {
		Object value = params.get(key);
		if (value == null || StringUtils.isEmpty(value.toString())) {
			throw new TemplateModelException("not found required parameter:"
					+ key + " for directive");
		}
		return value.toString();
	}

	@SuppressWarnings("unchecked")
	static String getParam(Map params, String key, String defaultValue)
			throws TemplateException {
		Object value = params.get(key);
		return value == null ? defaultValue : value.toString();
	}
}
