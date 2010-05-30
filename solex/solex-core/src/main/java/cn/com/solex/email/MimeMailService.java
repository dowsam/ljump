package cn.com.solex.email;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 邮件发送器 Freemarker模板，附件
 * 
 * @author xuenong_li
 * 
 */
public class MimeMailService {
	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory
			.getLogger(MimeMailService.class);

	private JavaMailSender mailSender;
	private Template template;
	private Configuration freemarkerConfiguration;

	public void send(String from, String to, String subject) {
		send(from, to, subject, null, null);
	}

	public void send(String from, String to, String subject,
			String templateName, Map<String, Object> map) {
		send(from, new String[] { to }, subject, templateName, map, null);
	}

	public void send(String from, String[] to, String subject,
			String templateName, Map<String, Object> map) {
		send(from, to, subject, templateName, map, null);
	}

	public void send(String from, String to, String subject,
			String templateName, Map<String, Object> map, String fileName) {
		send(from, new String[] { to }, subject, templateName, map, fileName);
	}

	public void send(String from, String[] to, String subject,
			String templateName, Map<String, Object> map, String fileName) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, DEFAULT_ENCODING);
			messageHelper.setFrom(from);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			if (templateName == null || "".equals(templateName)) {
				templateName = "base.ftl";
			}
			template = freemarkerConfiguration.getTemplate(templateName,DEFAULT_ENCODING);
			if (map == null || map.isEmpty()) {
				map = Maps.newHashMap();
			}
			String context = FreeMarkerTemplateUtils.processTemplateIntoString(
					template, map);
			messageHelper.setText(context,true);
			if (!(fileName == null || "".equals(fileName.trim()))) {
				Map<String, Object> fileMap = generateAttachment(fileName);
				messageHelper.addAttachment((String) fileMap.get("fileName"),
						(File) fileMap.get("file"));
			}
			mailSender.send(message);
			logger.info("HTML版邮件已发送至{}", StringUtils
					.arrayToCommaDelimitedString(to));
		} catch (MessagingException e) {
			logger.error("构造邮件失败", e);
		} catch (Exception e) {
			logger.error("发送邮件失败", e);
		}

	}

	private Map<String, Object> generateAttachment(String fileName)
			throws MessagingException {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Resource resource = new UrlResource(fileName);
			map.put("file", resource.getFile());
			map.put("fileName", resource.getFilename());
			return map;
		} catch (IOException e) {
			logger.error("构造邮件失败,附件文件不存在", e);
			throw new MessagingException("附件文件不存在", e);
		}
	}

	/**
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * freemarker configuration
	 * 
	 * @param freemarkerConfiguration
	 */
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}

}
