package com.example.question.controller;

import com.example.question.model.Question;
import com.example.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private QuestionService service;
    @PostMapping("/add")
    private Question add(@RequestBody Question question){
        return service.add(question);
    }
    @PostMapping("/vote/{q_id}/{voter_id}")
    private Boolean vote(@PathVariable String q_id,@PathVariable String voter_id){
        return service.vote(q_id,voter_id);
    }

}
