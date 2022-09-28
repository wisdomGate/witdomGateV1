package com.sa.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommendRunner implements CommandLineRunner {
    @Autowired
    private Service service;

    @Override
    public void run(String... args) throws Exception {

        service.sendMessage("Bereket Hailes");
    }
}
