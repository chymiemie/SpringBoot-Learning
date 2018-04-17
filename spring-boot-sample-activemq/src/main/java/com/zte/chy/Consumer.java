package com.zte.chy;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "${spring.activemq.queueName}")
	public void receiveQueue(String message) {
		System.out.println(message);
	}

}
