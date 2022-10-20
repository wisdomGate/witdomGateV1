package com.sa.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String id;
    private PaymentMethodes methodes;
    private double amount;
    private String email;
    private String FirstName;
    private String lastName;
}
