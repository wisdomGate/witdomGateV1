package com.sa.blog.controller;

import com.sa.blog.BlogResponse;
import com.sa.blog.model.Blog;
import com.sa.blog.model.Comment;
import com.sa.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private BlogService service;
    @PostMapping("/add")
    public Blog add(@RequestBody Blog blog){
        return service.add(blog);
    }
    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment){
        return service.add(comment);
    }
    @GetMapping("/get/{id}")
    public BlogResponse getBlog(@PathVariable String id){
        return service.getBlog(id);
    }
    @GetMapping("/getall")
    public List<Blog> getAll(){
        return service.getAllBlogs();
    }
    @DeleteMapping("/remove/{id}")
    public String deleteBlog(@PathVariable String id){
        return service.deleteBlog(id);
    }
}
