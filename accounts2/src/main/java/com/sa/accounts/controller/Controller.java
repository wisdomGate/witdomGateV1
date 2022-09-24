package com.sa.accounts.controller;

import com.sa.accounts.entity.Accounts;
import com.sa.accounts.repository.AccountRepo;
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
    private AccountRepo repo;
    @Autowired
    private MongoTemplate template;
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

    private Accounts useingTamplate(Accounts accounts) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(accounts.getEmail() ));
        List<Accounts> accounts1=template.find(query,Accounts.class);
        if(accounts1.size()>0){
            System.out.println("email booked");
            return null;
        }
        else
            return repo.insert(accounts);
    }

    @GetMapping("/get/{id}")
    public Accounts getAccount(@PathVariable int id){
        return repo.findById(id).orElse(null);
    }
    @GetMapping("/all")
    public List<Accounts> getall(){
        return repo.findAll();
    }

}
