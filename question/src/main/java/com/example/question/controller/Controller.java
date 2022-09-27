package com.example.question.controller;

import com.example.question.DTO.QuestionDTO;
import com.example.question.DTO.RequestDTO;
import com.example.question.DTO.Solution;
import com.example.question.DTO.SolutionDTO;
import com.example.question.model.Question;
import com.example.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class Controller {
    @Autowired
    private QuestionService service;
    @PostMapping("/add")
    public Question add(@RequestBody Question question){
        return service.add(question);
    }
    @PostMapping("/vote/{q_id}/{voter_id}")
    public String vote(@PathVariable String q_id,@PathVariable String voter_id){
        String str=service.vote(q_id,voter_id)?"true":"false";
        return str;
    }
    @GetMapping("/getall")
    public List<QuestionDTO> getQuestions(){
        return service.getAllQuestions();
    }
    @PostMapping("/getsolutions")
    public List<SolutionDTO> getSolution(@RequestBody RequestDTO id){
        System.out.println(id.getId());
        return service.getsolution(id.getId());
    }
    @GetMapping("/getPersonal/{id}")
    public List<QuestionDTO> getPersonal(@PathVariable String id){
        return service.personal(id);
    }

    @PostMapping("/addSolution")
    public ResponseEntity<Solution> addsol(@RequestBody Solution solution){
        return service.addSolution(solution);
    }
    @PostMapping("/solutionVote/{id}/{q_id}")
    public String goteSolution(@PathVariable String id, @PathVariable String q_id){
        return service.addsolutionVote(id,q_id)?"true":"false";
    }

}
