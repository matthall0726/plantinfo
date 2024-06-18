package com.discordapi.GoogleCloud;

import com.discordapi.SlashCommands.SlashCommand;
import com.google.cloud.spring.core.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Order(1)
@Service
public class CredentialsProvider {


    public CredentialsProvider(@Value("${Discord_TOKEN}") String discordToken) {
        SlashCommand.SlashCommandSetup(discordToken);
    }

}