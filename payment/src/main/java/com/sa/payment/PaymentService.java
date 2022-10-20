package com.sa.payment;

import com.sa.payment.payment.MailDTO;
import com.sa.payment.payment.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class PaymentService {
    @Autowired
    private RestTemplate template;
    @Autowired
    private RestTemplate restTemplate;
    public String makepayment(PaymentRequest request) {
        //String method=orderDIO.getPaymentType().getName();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Payload payload=new Payload();
        payload.setPrice(request.getAmount());
        payload.setFristName(request.getFirstName());
        payload.setLastName(request.getLastName());
        HttpEntity<Payload> requestEntity =
                new HttpEntity<>(payload, headers);
        System.out.println(request.getMethodes()+" "+ payload.getPrice());
        PaymentMethodes methodes=request.getMethodes();
        String response;
        if(methodes==PaymentMethodes.BANK){
             response=template.postForObject("http://localhost:8011/api/bank/make",requestEntity,String.class);
        } else if (methodes==PaymentMethodes.PAYPAL) {
             response=template.postForObject("http://localhost:8012/api/paypal/make",requestEntity,String.class);
        }else if (methodes==PaymentMethodes.CREDITCARD){
             response=template.postForObject("http://localhost:8010/api/creditcard/make",requestEntity,String.class);
        }else{
            return null;}


        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        MailDTO mailDTO=new MailDTO();
        mailDTO.setContent(" Dear "+request.getFirstName()+" "+request.getLastName()+"\n\tThank you for Donating or blog. \n"+response);
        mailDTO.setSubject("Account Transaction confirmation from WisdomGate \"don't  reply to this email!!\"");
        mailDTO.setTo(request.getEmail());

        HttpEntity<MailDTO> req =
                new HttpEntity<>(mailDTO, headers);
        ResponseEntity<String> str;
        str = restTemplate.postForEntity("http://mail-service:8084/sendMail",req,String.class);
        //System.out.println(str.getBody());
        return response;
    }
    public String make(){
        return "true";
    }
    public Boolean sendnotification(String email){
        return true;
    }
}
