package com.sa.blog.repository;

import com.sa.blog.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends MongoRepository<Blog, String> {
}
