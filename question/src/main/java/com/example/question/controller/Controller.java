package com.example.question.controller;

import com.example.question.DTO.QuestionDTO;
import com.example.question.model.Question;
import com.example.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private QuestionService service;
    @PostMapping("/add")
    public Question add(@RequestBody Question question){
        return service.add(question);
    }
    @PostMapping("/vote/{q_id}/{voter_id}")
    public Boolean vote(@PathVariable String q_id,@PathVariable String voter_id){
        return service.vote(q_id,voter_id);
    }
    @GetMapping("/getall")
    public List<QuestionDTO> getQuestions(){
        return service.getAllQuestions();
    }

}
