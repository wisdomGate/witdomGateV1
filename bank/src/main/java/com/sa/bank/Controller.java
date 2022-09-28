package com.sa.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class Controller {

    @PostMapping("/make")
    public String makepayment(@RequestBody Payload payload){
        return "succussfully transfered $"+payload.getPrice()+" from Bank";
    }



}
