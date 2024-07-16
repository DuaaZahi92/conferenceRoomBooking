package com.example.conferenceroombooking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties()
@ConfigurationProperties(prefix = "config")
@Setter
@Getter
public class Properties {
    private Map<String,String> availableRooms;
}
