package com.gap.domain.model.infrastructure;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

public class JMSDEConsumer {
    @Autowired
    ConfigurableApplicationContext context;

    public void receiveMessage(String message) {
        System.out.println("Message Received:" + message );
    }
}
