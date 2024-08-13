package com.test.microservices.limits_services.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-services")
@Getter
@Setter
public class Configuration {

    private int min;
    private int max;
}
