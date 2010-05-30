package cn.com.solex.security;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.RequestKey;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;


/**
 * 
 * 提供Spring Security Url_role
 * @author xuenong_li
 *
 */
public class DefinitionSourceFactoryBean implements FactoryBean<FilterInvocationSecurityMetadataSource> {

	private ResourceDetailsService resourceDetailsService;

	public void setResourceDetailsService(ResourceDetailsService resourceDetailsService) {
		this.resourceDetailsService = resourceDetailsService;
	}

	public FilterInvocationSecurityMetadataSource getObject() throws Exception {
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = buildRequestMap();
		UrlMatcher matcher = getUrlMatcher();
		DefaultFilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource = new DefaultFilterInvocationSecurityMetadataSource(
				matcher, requestMap);
		return defaultFilterInvocationSecurityMetadataSource;
	}

	private LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap() throws Exception {
		LinkedHashMap<String, String> srcMap = resourceDetailsService.getRequestMap();
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestToExpressionAttributesMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();

		for (Map.Entry<String, String> entry : srcMap.entrySet()) {
			RequestKey requestKey = new RequestKey(entry.getKey());
			List<ConfigAttribute> authsList = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			requestToExpressionAttributesMap.put(requestKey, authsList);
		}
		return requestToExpressionAttributesMap;
	}

	public Class<? extends FilterInvocationSecurityMetadataSource> getObjectType() {
		return FilterInvocationSecurityMetadataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	/**
	 * 提供Ant Style的URLMatcher.
	 */
	protected UrlMatcher getUrlMatcher() {
		return new AntUrlPathMatcher();
	}

}
