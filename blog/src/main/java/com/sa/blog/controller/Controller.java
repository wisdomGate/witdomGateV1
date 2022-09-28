package com.sa.blog.controller;

import com.sa.blog.BlogResponse;
import com.sa.blog.DTO.PaymentRequest;
import com.sa.blog.DTO.RequestDTO;
import com.sa.blog.ResponseDTO;
import com.sa.blog.model.Blog;
import com.sa.blog.model.Comment;
import com.sa.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
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
    @PostMapping("/get")
    public BlogResponse getBlog(@RequestBody RequestDTO id){
        return service.getBlog(id.getId());
    }
    @GetMapping("/getall")
    public List<Blog> getAll(){
        return service.getAllBlogs();
    }
    @DeleteMapping("/remove/{id}")
    public String deleteBlog(@PathVariable String id){
        return service.deleteBlog(id);
    }
    @PostMapping("/makeDonation")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest request){
        return service.makePayment(request);
    }

    @GetMapping("/search/{str}")
    public ResponseDTO search(@PathVariable String str){
        return service.search(str);
    }
}
