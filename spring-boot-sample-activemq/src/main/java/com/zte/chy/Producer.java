package com.zte.chy;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		send("Sample message");
		System.out.println("Message was send to the Queue");
	}
	
	public void send(String msg){
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}

}
