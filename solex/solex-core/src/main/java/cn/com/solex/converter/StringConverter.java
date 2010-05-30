package cn.com.solex.converter;

import org.apache.commons.beanutils.Converter;

/**
 * 字符串类型转换器
 * 
 * @author xuenong_li
 * 
 */
public final class StringConverter implements Converter {

	public StringConverter() {
	}

	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value) {
		if (value == null || "".equals(value.toString())) {
			return (String) null;
		} else {
			return value.toString();
		}
	}

}
