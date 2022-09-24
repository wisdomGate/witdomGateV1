package com.sa.solution.controller;

import com.sa.solution.model.Solution;
import com.sa.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private SolutionService service;
    @PostMapping("/add")
    private Solution add(@RequestBody Solution solution){
        return service.add(solution);
    }
    @PostMapping("/vote/{s_id}/{voter_id}")
    private Boolean vote(@PathVariable String s_id, @PathVariable String voter_id){
        return service.vote(s_id,voter_id);
    }
}
