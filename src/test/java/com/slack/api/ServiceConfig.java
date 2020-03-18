package com.slack.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("test.properties")
@ComponentScan("com.slack.api")
public class ServiceConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer test= new PropertySourcesPlaceholderConfigurer();
        test.setIgnoreUnresolvablePlaceholders(false);
        return test;
    }
}

