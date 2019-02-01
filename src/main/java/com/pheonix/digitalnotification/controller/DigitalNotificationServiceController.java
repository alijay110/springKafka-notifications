package com.pheonix.digitalnotification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pheonix.digitalnotification.model.DigitalEmailNotification;
import com.pheonix.digitalnotification.service.DigitalNotificationService;

@RestController
@RequestMapping("/customers/notifications")
public class DigitalNotificationServiceController {


	private DigitalNotificationService digitalnotificationService;

	@Autowired
	public DigitalNotificationServiceController(DigitalNotificationService digitalnotificationService) {

		this.digitalnotificationService = digitalnotificationService;
	}


	@RequestMapping(path = "/email", method = RequestMethod.POST)
	public ResponseEntity<String> sendEmail(@RequestBody DigitalEmailNotification digitalEmailNotification) {

		digitalnotificationService.sendEmail(digitalEmailNotification);
		return new ResponseEntity("notification to sent email created", HttpStatus.CREATED);
	}

	

}
