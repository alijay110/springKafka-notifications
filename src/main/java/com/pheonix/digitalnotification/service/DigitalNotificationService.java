package com.pheonix.digitalnotification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.pheonix.digitalnotification.model.DigitalEmailNotification;
import com.pheonix.digitalnotification.model.RegistrationEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DigitalNotificationService {

	@Autowired
	private MailClient mailClient;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	public void sendEmail(DigitalEmailNotification digitalEmailNotification) {

		 log.info("digitalEmailNotification sending email");
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(digitalEmailNotification.getEmail().getFrom());
			messageHelper.setTo(digitalEmailNotification.getEmail().getTo());
			messageHelper.setSubject(digitalEmailNotification.getEmail().getSubject());
			String content = mailContentBuilder.build(digitalEmailNotification.getEmail(),
								digitalEmailNotification.getEmailCustomerDetails());
			messageHelper.setText(content, true);
		};

		
		mailClient.prepareAndSend(messagePreparator);

	}
	
	@Value("{spring.mail.username}")
	private String defaultFromEmail;
	
	public void sendEmailForRegistration(RegistrationEvent registrationEvent,String subject, String body) {

		 log.info("sendEmailForRegistration email for registration");
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(defaultFromEmail);
			messageHelper.setTo(registrationEvent.getEmail());
			messageHelper.setSubject(null!=subject?subject:"Registration Details");
			String content = mailContentBuilder.build(registrationEvent,body);
			messageHelper.setText(content, true);
		};

		
		mailClient.prepareAndSend(messagePreparator);

	}


}
