package com.sa.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("/make")
    public String makepayment(@RequestBody Payload payload){
        return "succussfully transfered $"+payload.getPrice()+" from Bank";
    }
    @PostMapping("/sent/{message}")
    public ResponseEntity<String> send(@PathVariable String message){
        service.sendMessage(message);
        return new ResponseEntity<>("message sent: "+message, HttpStatus.OK);
    }



}
