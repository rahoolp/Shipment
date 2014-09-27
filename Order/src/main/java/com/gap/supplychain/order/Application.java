package com.gap.supplychain.order;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.gap.supplychain.order.config.AppConfig;


public class Application {
    
	public static void main(String[] args) throws IllegalStateException, Exception {
		
        ConfigurableApplicationContext context = SpringApplication.run(AppConfig.class, args);
	}

}

