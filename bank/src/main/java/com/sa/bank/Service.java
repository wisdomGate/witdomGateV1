package com.sa.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private JmsTemplate template;
    @Value(value = "${springjms.safinal}")
    String jsmQueue;

    public void sendMessage(String message){
        template.convertAndSend(jsmQueue,message );
    }
}
