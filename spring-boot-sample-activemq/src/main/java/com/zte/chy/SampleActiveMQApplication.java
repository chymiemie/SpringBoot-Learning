package com.zte.chy;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableJms
public class SampleActiveMQApplication {

	@Bean
	public Queue queue(@Value ("${spring.activemq.queueName}") String queueName){
		return new ActiveMQQueue(queueName);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SampleActiveMQApplication.class, args);
	}
}
