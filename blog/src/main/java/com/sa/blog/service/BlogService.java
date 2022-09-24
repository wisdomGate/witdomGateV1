package com.sa.blog.service;

import com.sa.blog.BlogResponse;
import com.sa.blog.model.Blog;
import com.sa.blog.model.Comment;
import com.sa.blog.repository.BlogRepo;
import com.sa.blog.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepo repo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private MongoTemplate template;
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
}
