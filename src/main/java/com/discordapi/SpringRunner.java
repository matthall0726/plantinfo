package com.discordapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@SpringBootApplication
@ComponentScan(basePackages =  {"com.discordapi.Controller"})
@EnableMongoRepositories
public class SpringRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringRunner.class, args);

	}


}
