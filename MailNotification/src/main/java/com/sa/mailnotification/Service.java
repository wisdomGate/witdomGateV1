package com.sa.mailnotification;

//import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.jms.Message;
import java.util.Date;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private JavaMailSender mailSender;

//    @Autowired
//    private JmsTemplate jmsTemplate;
//    @Autowired
//    private MessageConverter messageConverter;
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
    public String sendemail(String email){
        return "true";
    }
    @JmsListener(destination = "${springjms.safinal}")
    public void send(MailDTO mailDTO){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(mailDTO.getTo());
        message.setText(mailDTO.getContent());
        message.setSentDate(new Date());
        message.setSubject(mailDTO.getSubject());
        message.setFrom("haben.noreply@gmail.com");
        mailSender.send(message);
        System.out.println("mail sent");
    }
    //@JmsListener(destination = "${springjms.safinal}")
//    public void  receiverMessage(){
//        try{
//            Message message= jmsTemplate.receive();
//            assert message != null;
//            MailDTO dto=(MailDTO) messageConverter.fromMessage(message);
//            SimpleMailMessage message1=new SimpleMailMessage();
//            message1.setTo(dto.getTo());
//            message1.setText(dto.getContent());
//            message1.setSentDate(new Date());
//            message1.setSubject(dto.getSubject());
//            message1.setFrom("haben.noreply@gmail.com");
//            mailSender.send(message1);
//            System.out.println("mail sent");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }
}
