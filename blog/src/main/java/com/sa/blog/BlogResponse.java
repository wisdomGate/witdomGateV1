package com.sa.blog;

import com.sa.blog.model.Blog;
import com.sa.blog.model.Comment;
import lombok.Data;

import java.util.List;
@Data
public class BlogResponse {
    private Blog blog;
    protected List<Comment> comments;
}
