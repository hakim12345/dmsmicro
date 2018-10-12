package com.dms.recieveClient.email;

import com.dms.recieveClient.model.EmailLog;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import java.io.IOException;

public interface HtmlEmailSender {

	/**
	 * @param email **email**.
	 * @param emailLog **emailLog**.
	 * @throws Exception **Exception**.
	 */ 
	public void send(final Email email, EmailLog emailLog) throws Exception;

	/**
	 * @param emailConfig **emailConfig**.
	 */
	public void setEmailConfig(EmailConfig emailConfig);

	void sendSimpleEmail(SimpleMailMessage simpleMailMessage);

	void prepareAndSend(Email email, EmailLog emailLog) throws MessagingException, IOException;
}