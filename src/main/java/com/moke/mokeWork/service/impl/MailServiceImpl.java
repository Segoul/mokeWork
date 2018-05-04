package com.moke.mokeWork.service.impl;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.moke.mokeWork.bean.dto.mail.MailParamForm;
import com.moke.mokeWork.service.MailService;

/**
 * 邮件ServiceImpl
 * @author Segoul
 *
 */

@Service
public class MailServiceImpl implements MailService {

	@Autowired
    private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
    private String sender;  //发送者
	
	@Value("${vm.store.address}")
    private String vmStore;  //vm模板存放地址
	
	/**
	 * 发送HTML页面的邮件（包括普通邮件）
	 * @param form
	 */
	public void sendHtmlMail(MailParamForm form){
		
		String mailTo = form.getMailTo();  //邮件接收地址
		String title = form.getTitle();  //邮件标题
		String content = form.getContent();  //邮件内容
		
		//配置邮件基本信息
		MimeMessage message = null;
		try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setFrom(sender);
            helper.setTo(mailTo);
            helper.setSubject(title);
            helper.setText(content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//发送邮件
        mailSender.send(message);
	}
	
	/**
	 * 发送带有附件的邮件
	 * @param form
	 */
	public void sendAttachMail(MailParamForm form){
		
		String mailTo = form.getMailTo();  //邮件接收地址
		String title = form.getTitle();  //邮件标题
		String content = form.getContent();  //邮件内容
		String filePath = form.getFilePath();  //文件地址
		String fileName = form.getFileName();  //文件名称
		String fileType = form.getFileType();  //文件类型后缀
		
		//配置邮件基本信息
    	MimeMessage message = null;
    	try{
    		message = mailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);
    		helper.setFrom(sender);
    		helper.setTo(mailTo);
    		helper.setSubject(title);
    		helper.setText(content,true);
    		FileSystemResource file = new FileSystemResource(new File(filePath));
    		helper.addAttachment(fileName+"."+fileType, file);
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	
    	//发送邮件
    	mailSender.send(message);
	}

	/**
	 * 发送velocity模板邮件
	 * @param form
	 */
	public void sendVelocityTemplateMail(MailParamForm form){
		
		String mailTo = form.getMailTo();  //邮件接收地址
		String title = form.getTitle();  //邮件标题
		String templateName = form.getTemplateName();  //模板名称
		Map model = form.getModel();  //模板参数
		
		//配置邮件基本信息
    	MimeMessage message = null;
    	try{
    		message = mailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);
    		helper.setFrom(sender);
    		helper.setTo(mailTo);
    		helper.setSubject(title);
    		
    		//初始化velocity引擎
    		//String fileDir = MailServiceImpl.class.getResource("/templates").getPath();
    		VelocityEngine velocityEngine = new VelocityEngine();
    		Properties properties = new Properties();
    		properties.setProperty(velocityEngine.FILE_RESOURCE_LOADER_PATH, vmStore);
    		velocityEngine.init(properties);
    		
    		helper.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "utf-8", model),true);
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	
    	//发送邮件
    	mailSender.send(message);
	}
	
	/**
	 * 发送带有附件的velocity模板邮件
	 * @param form
	 */
	public void sendVelocityTemplateAttachMail(MailParamForm form){
		
		String mailTo = form.getMailTo();  //邮件接收地址
		String title = form.getTitle();  //邮件标题
		String filePath = form.getFilePath();  //文件地址
		String fileName = form.getFileName();  //文件名称
		String fileType = form.getFileType();  //文件类型后缀
		String templateName = form.getTemplateName();  //模板名称
		Map model = form.getModel();  //模板参数
		
		//配置邮件基本信息
    	MimeMessage message = null;
    	try{
    		message = mailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);
    		helper.setFrom(sender);
    		helper.setTo(mailTo);
    		helper.setSubject(title);
    		
    		//初始化velocity引擎
    		//String fileDir = MailServiceImpl.class.getResource("/templates").getPath();
    		VelocityEngine velocityEngine = new VelocityEngine();
    		Properties properties = new Properties();
    		properties.setProperty(velocityEngine.FILE_RESOURCE_LOADER_PATH, vmStore);
    		velocityEngine.init(properties);
    		
    		helper.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "utf-8", model),true);
    		
    		//添加附件
    		FileSystemResource file = new FileSystemResource(new File(filePath));
    		helper.addAttachment(fileName+"."+fileType, file);
    		
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	
    	//发送邮件
    	mailSender.send(message);
	}
}
