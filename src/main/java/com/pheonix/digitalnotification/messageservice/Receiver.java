package com.pheonix.digitalnotification.messageservice;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pheonix.digitalnotification.model.RegistrationEvent;
import com.pheonix.digitalnotification.service.DigitalNotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {
	
	private CountDownLatch latch = new CountDownLatch(5);

	private CountDownLatch partitionLatch = new CountDownLatch(5);
	
	public CountDownLatch getPartitionLatch() {

        return partitionLatch;
    }
	
    public CountDownLatch getLatch() {

        return latch;
    }
    
    @Autowired
    private DigitalNotificationService digitalNotificationService;

    
    @KafkaListener(topics = "${spring.kafka.topic.dgs-customerRegistration}", containerFactory = "digitalAppCreatedKafkaListenerContainerFactory")
    public void receiveAppRegistration(RegistrationEvent dgs) {

        log.info("receiveAppRegistration payload='{}'", dgs);
        digitalNotificationService.sendEmailForRegistration(dgs, "Registration to Pheonix", null);
        
        latch.countDown();
       
    } 
    
   
}
