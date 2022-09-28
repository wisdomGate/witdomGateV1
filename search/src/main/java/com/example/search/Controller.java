package com.example.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class Controller {
    @Autowired
    private Service service;
    @PostMapping
    public Object search(@RequestBody SearchDTO query){
        return service.search(query);
    }
}
