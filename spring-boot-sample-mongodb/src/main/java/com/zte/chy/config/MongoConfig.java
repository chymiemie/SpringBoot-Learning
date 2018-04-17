package com.zte.chy.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {

	@Bean(name = "mongoProperties")
	@Primary
	@ConfigurationProperties(prefix = "spring.data.mongodb")
	public MongoProperties mongoProperties() {
		return new MongoProperties();
	}

	@Bean(name = "mongoDbFactoryPrimary")
	@Primary
	public MongoDbFactory mongoDbFactoryFirst(@Qualifier(value = "mongoProperties") MongoProperties mongoProperties)
			throws UnknownHostException {
		return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getHost(), mongoProperties.getPort()),
				mongoProperties.getDatabase());
	}

	@Bean
	@Primary
	public MongoTemplate mongoTemplate(@Qualifier("mongoDbFactoryPrimary") MongoDbFactory mongoDbFactory) {
		return new MongoTemplate(mongoDbFactory);
	}

}
