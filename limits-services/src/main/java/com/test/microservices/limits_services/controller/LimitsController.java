package com.test.microservices.limits_services.controller;

import com.test.microservices.limits_services.bean.Limit;
import com.test.microservices.limits_services.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limit getLimits(){

        return new Limit(configuration.getMin(),configuration.getMax());
    }
}
