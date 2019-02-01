package com.pheonix.digitalnotification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pheonix.digitalnotification.model.EmailCustomerDetails;
import com.pheonix.digitalnotification.model.EmailInformation;

@Service
public class MailContentBuilder {
	
	private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    public String build(EmailInformation emailInformation, EmailCustomerDetails emailCustomerDetails) {
        Context context = new Context();
        context.setVariable("title", emailCustomerDetails.getName().getTitle());
        context.setVariable("firstName", emailCustomerDetails.getName().getFirstName());
        context.setVariable("lastName", emailCustomerDetails.getName().getLastName());
        
        context.setVariable("message", emailInformation.getMessage());
        return templateEngine.process("mailTemplate", context);
    }


    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }


}
