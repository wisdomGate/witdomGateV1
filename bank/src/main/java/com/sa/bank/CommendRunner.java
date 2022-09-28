package com.sa.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommendRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Service service=new Service();
        service.sendMessage("Bereket Hailes");
    }
}
