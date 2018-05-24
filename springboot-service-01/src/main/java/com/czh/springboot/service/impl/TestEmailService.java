package com.czh.springboot.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * <p>
 * Title: TestEmailService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: www.chenzhehao.com
 * </p>
 * 
 * @author chenzhehao
 * @date 2018年4月12日
 * @version 1.0
 */

@Service("testEmailService")
public class TestEmailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	Configuration configuration; // freeMarker configuration

	public String sendEmail() {// 发送邮件测试--简单邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("852347470@qq.com");
		message.setTo("chenzhehao2008666@icloud.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
		return "success";
	}

	public String sendEmail1() throws MessagingException {// 发送邮件测试--带附件内容
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("852347470@qq.com");
		helper.setTo("chenzhehao2008666@icloud.com");
		helper.setSubject("主题：有附件、嵌入静态资源");

		// 模板邮件velocity
		Map<String, Object> model = new HashMap();
		model.put("username", "陈哲浩");
		Template t;
		try {
			t = configuration.getTemplate("template.ftl");
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			helper.setText(content, true);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} // freeMarker template

		// 发送附件
		FileSystemResource file = new FileSystemResource(new File("/Users/chenzhehao/Desktop/odd.txt"));
		helper.addAttachment("附件-1.txt", file);
		helper.addAttachment("附件-2.txt", file);

		FileSystemResource pic = new FileSystemResource(new File("/Users/chenzhehao/Desktop/20160712110935064.png"));
		// 嵌入静态资源
		helper.addInline("weixin", pic);

		mailSender.send(mimeMessage);
		return "success";
	}
}
