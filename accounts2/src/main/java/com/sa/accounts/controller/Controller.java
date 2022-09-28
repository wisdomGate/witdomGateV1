package com.sa.accounts.controller;

import com.sa.accounts.Dto.OwnerDTO;
import com.sa.accounts.ResponseDTO;
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
@RequestMapping("/api/account")
public class Controller {
    @Autowired
    private AccountService service;
    @Autowired
    private AccountRepo repo;

    @PostMapping("/add")
    public String saveAccount(@RequestBody Accounts accounts){
//        repo.findAccountsByEmail(accounts.getEmail()).ifPresentOrElse(a->{
//            System.out.println("email already exist");
//
//        },()->{
//            System.out.println("inserted");
//            repo.insert(accounts);
//        });
        Accounts acc=service.save(accounts);
        String status=acc!=null?"true":"flase";
        return status;
    }
    @GetMapping("/getOwner/{id}")
    public OwnerDTO getOwner(@PathVariable String id){
        return service.getOwner(id);
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
        System.out.println(id+"  "+f);
        return service.follow(id,f);
    }
    @GetMapping("/authenticate/{username}/{password}")
    public Accounts authenticate(@PathVariable String username,@PathVariable String password){
        return service.authenticate(username,password);
    }
    @GetMapping("/getFollers/{id}")
    public Integer getFollowers(@PathVariable String id){
        return service.getnumberofFollowers(id);
    }
    @GetMapping("/search/{str}")
    public ResponseDTO search(@PathVariable String str){
        return service.search(str);
    }

}
