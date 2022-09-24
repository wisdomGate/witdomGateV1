package com.sa.accounts.service;

import com.sa.accounts.entity.Accounts;
import com.sa.accounts.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepo repo;
    @Autowired
    private MongoTemplate template;

    private String updatePassword(String email,String pass) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email ));
        Accounts accounts1=template.findOne(query,Accounts.class);
        assert accounts1 != null;
        accounts1.setPassword(pass);
        template.save(accounts1);
        return "success";
    }
    public Boolean authenticate(String username,String password){
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(username ));
        Accounts accounts1=template.findOne(query,Accounts.class);
        return accounts1.getPassword().equals(password);
    }
    public Integer getnumberofFollowers(String id){
        Accounts accounts=repo.findById(id).orElse(null);
        assert accounts!=null;
        return accounts.getFollowers().size();

    }
    public Accounts save(Accounts accounts){
        Accounts acc=repo.findAccountsByEmail(accounts.getEmail()).orElse(null);
        if(acc!=null){
            return repo.save(accounts);
        }else
            return null;
    }
    public String follow(String follower,String followed){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(follower));
       Accounts owner=template.findOne(query,Accounts.class,"accounts");
       query=null;
        System.out.println(owner.toString());
        List<String> acc=new ArrayList<>();
        if(owner.getFollowings()!=null){
            acc=owner.getFollowings();
            if(acc.contains(followed))
                acc.remove(followed);
            else
                acc.add(followed);

        }else{
            acc.add(followed);
        }
        owner.setFollowings(acc);
       repo.save(owner);
       query=new Query();
        query.addCriteria(Criteria.where("_id").is(followed));
        acc=new ArrayList<>();
       Accounts second=template.findOne(query,Accounts.class,"accounts");
       query=null;
        if(second.getFollowers()!=null){
            acc=second.getFollowers();
            if(acc.contains(follower))
                acc.remove(follower);
            else
                acc.add(follower);

        }else{
            acc.add(follower);
        }
        second.setFollowers(acc);
       repo.save(second);
       return "followed";
    }

}
