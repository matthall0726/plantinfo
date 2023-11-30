package com.discordapi;

import com.discordapi.Controller.DiscordController;
import com.discordapi.GoogleCloud.CredentialsProvider;
import com.discordapi.Repository.PlantRepository;

import com.discordapi.SlashCommands.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;
import java.util.Objects;


@SpringBootApplication
@ComponentScan(basePackages =  {"com.discordapi.Controller"})
@EnableMongoRepositories
public class SpringRunner {

	@Autowired
	PlantRepository plantRepository;




	public static void main(String[] args) {
		SpringApplication.run(SpringRunner.class, args);

	}


}
