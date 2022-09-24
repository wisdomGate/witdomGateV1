package com.sa.accounts.controller;

import com.sa.accounts.entity.Accounts;
import com.sa.accounts.repository.AccountRepo;
import com.sa.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
//@RequiredArgsConstructor
public class Controller {
    @Autowired
    private AccountService service;
    @Autowired
    private AccountRepo repo;

    @PostMapping("/add")
    public Accounts saveAccount(@RequestBody Accounts accounts){
        repo.findAccountsByEmail(accounts.getEmail()).ifPresentOrElse(a->{
            System.out.println("email already exist");

        },()->{
            System.out.println("inserted");
            repo.insert(accounts);
        });
        return accounts;
    }



    @GetMapping("/get/{id}")
    public Accounts getAccount(@PathVariable String id){
        return repo.findById(id).orElse(null);
    }
    @GetMapping("/all")
    public List<Accounts> getall(){
        return repo.findAll();
    }
    @PostMapping("/follow/{id}/{f}")
    public String follow(@PathVariable String id, @PathVariable String f){
        return service.follow(id,f);
    }

}
