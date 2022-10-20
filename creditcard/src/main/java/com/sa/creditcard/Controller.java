package com.sa.creditcard;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditcard")
public class Controller {
    @PostMapping("/make")
    public String makepayment(@RequestBody Payload payload){
        return "succussfully transfered $"+payload.getPrice()+" from Credit Card";
    }
}
