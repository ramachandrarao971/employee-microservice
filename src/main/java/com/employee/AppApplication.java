package com.employee;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;


@SpringBootApplication
public class AppApplication {
	@Value("${spring.data.mongodb.uri}")
	private String url;
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoClient mongoClient) {
		return new MongoTemplate(mongoClient, "application");
	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(this.url);
	}
}
