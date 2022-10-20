package com.sa.mailnotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("/sendMail")
    public String  sendMaile(@RequestBody MailDTO mailDTO){
        service.sendSimpleMail(mailDTO);
        return "we send a confermation email to "+mailDTO.getTo();
    }
}
