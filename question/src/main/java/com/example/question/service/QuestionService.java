package com.example.question.service;

import com.example.question.model.Question;
import com.example.question.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo repo;
    @Autowired
    private MongoTemplate template;
    public Question add(Question question){
        return repo.save(question);
    }

    public Boolean vote( String q_id, String voter_id){
        Question question=repo.findById(q_id).orElse(null);
        System.out.println(q_id);
        boolean status=false;
        List<String> accounts=new ArrayList<>();
        if(question!=null){
            if(question.getUpvote()!=null)
                accounts=question.getUpvote();
            if(!accounts.contains(voter_id)){
                status=true;
                accounts.add(voter_id);
            }else
                accounts.remove(voter_id);

        }
        question.setUpvote(accounts);
        repo.save(question);
        return status;
    }

}
