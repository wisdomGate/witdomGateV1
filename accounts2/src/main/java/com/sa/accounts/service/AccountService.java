package com.sa.accounts.service;

import com.sa.accounts.Dto.Conversation;
import com.sa.accounts.Dto.OwnerDTO;
import com.sa.accounts.MailDTO;
import com.sa.accounts.ResponseDTO;
import com.sa.accounts.entity.Accounts;
import com.sa.accounts.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepo repo;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MongoTemplate template;

    @Autowired
    private RestTemplate restTemplate;

    private String updatePassword(String email,String pass) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email ));
        Accounts accounts1=template.findOne(query,Accounts.class);
        assert accounts1 != null;
        accounts1.setPassword(pass);
        template.save(accounts1);
        return "success";
    }
    public List<Accounts> getAll(){
        return repo.findAll();
    }
    public Accounts authenticate(String username,String password){
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(username ));
        Accounts accounts1=template.findOne(query,Accounts.class);
        //AuthenticationResponse response=new AuthenticationResponse();
        //response=(AuthenticationResponse) accounts1;
        if(accounts1.getPassword().equals(password)){
            accounts1.setPassword(null);
            return accounts1;
        }
        return null;
    }
//    @Value(value = "${springjms.safinal}")
//    String jsmQueue;
    public void  sendMail(MailDTO mailDTO){
        jmsTemplate.send(session -> session.createObjectMessage((Serializable) mailDTO));

    }
    public Integer getnumberofFollowers(String id){
        Accounts accounts=repo.findById(id).orElse(null);
        if(accounts==null)
            return 0;
        else
            return accounts.getFollowers().size();

    }
    public Accounts save(Accounts accounts){
        Accounts acc=repo.findAccountsByEmail(accounts.getEmail()).orElse(null);
       // System.out.println("here0"+ acc.getEmail());
        if(acc!=null){
            return null;
        }else{
            MailDTO mailDTO=new MailDTO();
            mailDTO.setContent(" your account successfully created thank you for being member of WisdomGate");
            mailDTO.setSubject("Account verification from WisdomGate \"don't  reply to this email!!\"");
            mailDTO.setTo(accounts.getEmail());

//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//
//            HttpEntity<MailDTO> requestEntity =
//                    new HttpEntity<>(mailDTO, headers);
//            ResponseEntity<String> str;
//            str = restTemplate.postForEntity("http://localhost:8084/sendMail",requestEntity,String.class);
//            System.out.println(str);
            sendMail(mailDTO);
            return repo.save(accounts);
        }

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
            if(acc.contains(followed)){
                restTemplate.delete("http://localhost:8083/api/chatroom/delete/"+followed+"/"+follower);
                acc.remove(followed);
            }
            else{
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                Conversation conversation=new Conversation();
                conversation.setMembers(Arrays.asList(follower,followed));

                HttpEntity<Conversation> requestEntity =
                        new HttpEntity<>(conversation, headers);
                restTemplate.postForEntity("http://localhost:8083/api/chatroom/addConvesrsation",requestEntity, Conversation.class);
                acc.add(followed);
            }

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

    public OwnerDTO getOwner(String id) {
        Accounts accounts=repo.findById(id).orElse(null);
        OwnerDTO dto=new OwnerDTO();
        assert accounts != null;
        dto.setOwnerId(accounts.getId());
        dto.setOwnerFristName(accounts.getFirstName());
        dto.setOwnerLastName(accounts.getLastName());
        return dto;
    }

    public ResponseDTO search(String str) {
        Criteria criteria=new Criteria();
        criteria.orOperator(Criteria.where("firstName").regex(str),Criteria.where("lastName").regex(str));
        Query query=new Query(criteria);
        List<Accounts> accounts=template.find(query,Accounts.class);
        ResponseDTO dto=new ResponseDTO();
        dto.setResponse(Collections.singletonList(accounts));
        return dto;
    }
}
