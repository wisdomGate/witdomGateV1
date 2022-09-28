package com.sa.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReciverService {
    Logger log= LoggerFactory.getLogger(ReciverService.class);
    @JmsListener(destination = "${springjms.safinal}")
    public void  receiverMessage(String message){
        log.info("received message "+ message);
    }
}
