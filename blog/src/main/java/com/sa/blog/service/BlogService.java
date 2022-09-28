package com.sa.blog.service;

import com.sa.blog.BlogResponse;
import com.sa.blog.DTO.PaymentRequest;
import com.sa.blog.ResponseDTO;
import com.sa.blog.model.Blog;
import com.sa.blog.model.Comment;
import com.sa.blog.repository.BlogRepo;
import com.sa.blog.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepo repo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private RestTemplate restTemplate;
    public Blog add(Blog blog){
        return repo.save(blog);
    }

    public String deleteBlog(String b_id){
        Blog blog=repo.findById(b_id).orElse(null);
        repo.deleteById(b_id);
        Query query=new Query();
        query.addCriteria(Criteria.where("blog_id").is(b_id));
        template.remove(query,Comment.class);
        assert blog != null;
        return "Blog with title "+blog.getTitle()+"... deleted";
    }
    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }
    public Comment add(Comment comment){
        return commentRepo.save(comment);
    }
    public BlogResponse getBlog(String id){
        Blog blog=repo.findById(id).orElse(null);
        Query query=new Query();
        query.addCriteria(Criteria.where("blog_id").is(id));
        List<Comment> comments=template.find(query,Comment.class);
        BlogResponse response=new BlogResponse();
        response.setBlog(blog);
        response.setComments(comments);
        return response;
    }
    public List<Blog> getAllBlogs(){
        return repo.findAll();
    }

    public ResponseEntity<String> makePayment(PaymentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
//        Conversation conversation=new Conversation();
//        conversation.setMembers(Arrays.asList(follower,followed));

        HttpEntity<PaymentRequest> requestEntity =
                new HttpEntity<>(request, headers);
        ResponseEntity<String> str=restTemplate.postForEntity("http://payment-service:8085/api/payment/make",requestEntity, String.class);
        return str;
    }
    public ResponseDTO search(String str) {
        Criteria criteria=new Criteria();
        criteria.orOperator(Criteria.where("title").regex(str));
        Query query=new Query(criteria);
        List<Blog> blogs=template.find(query,Blog.class);
        ResponseDTO dto=new ResponseDTO();
        dto.setResponse(Collections.singletonList(blogs));
        return dto;
    }
}
