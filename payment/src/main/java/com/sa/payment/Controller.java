package com.sa.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class Controller {
    @Autowired
    private PaymentService service;
    @PostMapping("/make")
    public String makePayment(@RequestBody PaymentRequest request){
       // System.out.println(orderDIO.getOrder().getTotal_price()+" "+orderDIO.getPaymentType().toString());
        return service.makepayment(request);
    }
}
