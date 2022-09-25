package com.sa.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Accounts2Application {

    public static void main(String[] args) {
        SpringApplication.run(Accounts2Application.class, args);
    }
    @Bean
    public RestTemplate gettemplate(){
        return new RestTemplate();
    }

}
