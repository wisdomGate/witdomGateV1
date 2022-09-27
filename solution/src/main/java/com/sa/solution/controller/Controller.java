package com.sa.solution.controller;

import com.sa.solution.SolResponse;
import com.sa.solution.service.SolutionService;
import com.sa.solution.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getSolution/{id}")
    public SolResponse getall(@PathVariable String id){
        System.out.println(id);
        return service.getAll(id);
    }
}
