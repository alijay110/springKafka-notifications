package com.pheonix.digitalnotification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.pheonix.digitalnotification.model.DigitalEmailNotification;

@Service
public class DigitalNotificationService {

	@Autowired
	private MailClient mailClient;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	public void sendEmail(DigitalEmailNotification digitalEmailNotification) {


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


}
