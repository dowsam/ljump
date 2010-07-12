package cn.com.solex.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.validator.resourceloading.ResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;

/**
 * 将ResourceBundleLocator代理为spring的MessageSource
 * 
 * <pre>
 * 用法:
 *    &lt;!-- 用于启用Hibernate Validator -->
 * 	&lt;bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean" >
 * 		&lt;property name="messageInterpolator" ref="messageInterpolator"/>
 * 	&lt;/bean>
 * 
 * 	&lt;bean id="messageInterpolator" class="org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator" >
 * 		&lt;constructor-arg ref="resourceBundleLocator"></constructor-arg>
 * 	&lt;/bean>
 * </pre>
 * 
 * <pre>
 * spring配置:
 * &lt;bean id="resourceBundleLocator" class="cn.com.solex.utils.MessageSourceResourceBundleLocator">
 * &lt;property name="messageSource"> 
 * &lt;bean id="validationMessagesMessageSource" class ="org.springframework.context.support.ReloadableResourceBundleMessageSource">
 * &lt;property name="basename" value="classpath:ValidationMessages"/> 
 * &lt;property name="defaultEncoding" value="UTF-8"/> 
 * &lt;property name="cacheSeconds" value="600"/> 
 * &lt;/bean> 
 * &lt;/property> 
 * &lt;/bean>
 * </pre>
 * 
 * @author l.xue.nong
 * 
 */
public class MessageSourceResourceBundleLocator implements
		ResourceBundleLocator {

	private MessageSource messageSource;

	public MessageSourceResourceBundleLocator() {
	}

	public MessageSourceResourceBundleLocator(MessageSource messageSource) {
		setMessageSource(messageSource);
	}

	public void setMessageSource(MessageSource messageSource) {
		if (messageSource == null)
			throw new IllegalArgumentException(
					"'messageSource' must be not null");
		this.messageSource = messageSource;
	}

	public ResourceBundle getResourceBundle(Locale locale) {
		return new MessageSourceResourceBundle(messageSource, locale);
	}

}
