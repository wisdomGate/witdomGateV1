package com.sa.mailnotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

@SpringBootApplication
@EnableJms
public class MailNotificationApplication  {
    @Autowired
    private Service service;
    public static void main(String[] args) {
        SpringApplication.run(MailNotificationApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        service.sendSimpleMail("hello bereket kemey aleka","bhbbereket5@gmail.com","test");
//    }

}
