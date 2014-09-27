package com.gap.supplychain.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.gap.supplychain.order.application.OrderService;
import com.gap.supplychain.order.domain.OrderRepository;
import com.gap.supplychain.order.infrastratucture.persistance.MongoOrderRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gap")
@PropertySource("classpath:application.properties")
public class AppConfig {

		
    @Value("${mongo.host}")
    private String host; 

    @Value("${mongo.port}")
    private int port;

    @Value("${mongo.dbName}")
    private String dbName;

    @Value("${mongo.collectionName}")
    private String collectionName;
    
	@Bean
	public OrderRepository shipmentRepository() throws Exception {
		
		OrderRepository shipmentRepository = new MongoOrderRepository(host, port, dbName, collectionName);
		return shipmentRepository;
		
	}
	
	@Bean
	public OrderService getShipmentService(OrderRepository shipmentRepository) {
		return new OrderService(shipmentRepository);
	}


}
