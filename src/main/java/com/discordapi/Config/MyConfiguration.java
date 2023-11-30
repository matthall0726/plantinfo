package com.discordapi.Config;

import org.springframework.cloud.context.properties.ConfigurationPropertiesBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public ConfigurationPropertiesBeans configurationPropertiesBeans() {
        return new ConfigurationPropertiesBeans();
    }
}