package com.moke.mokeWork.service;

import com.moke.mokeWork.bean.dto.mail.MailParamForm;

/**
 * 邮件Service
 * @author Segoul
 *
 */

public interface MailService {

	/**
	 * 发送HTML页面的邮件（包括普通邮件）
	 * @param form
	 */
	void sendHtmlMail(MailParamForm form);
	
	/**
	 * 发送带有附件的邮件
	 * @param form
	 */
	void sendAttachMail(MailParamForm form);

	/**
	 * 发送velocity模板邮件
	 * @param form
	 */
	void sendVelocityTemplateMail(MailParamForm form);
	
	/**
	 * 发送带有附件的velocity模板邮件
	 * @param form
	 */
	void sendVelocityTemplateAttachMail(MailParamForm form);
}
