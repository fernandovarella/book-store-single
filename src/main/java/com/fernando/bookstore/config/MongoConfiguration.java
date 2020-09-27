package com.fernando.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.fernando.bookstore");
public class MongoConfiguration {
    
}
