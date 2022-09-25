package com.sa.mailnotification;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private JavaMailSender mailSender;
    public void sendSimpleMail(MailDTO mailDTO){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(mailDTO.getTo());
        message.setText(mailDTO.getContent());
        message.setSentDate(new Date());
        message.setSubject(mailDTO.getSubject());
        message.setFrom("haben.noreply@gmail.com");
        mailSender.send(message);
        System.out.println("mail sent");

    }
}
