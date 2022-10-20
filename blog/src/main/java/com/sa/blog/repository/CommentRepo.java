package com.sa.blog.repository;

import com.sa.blog.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends MongoRepository<Comment,String> {
    //void  deleteAllByOwner_id(String id);
}
