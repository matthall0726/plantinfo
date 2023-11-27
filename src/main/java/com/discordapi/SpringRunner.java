package com.discordapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages =  {"com.discordapi.Controller"})
public class SpringRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringRunner.class, args);
	}

}
