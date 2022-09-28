package com.sa.blog;

import com.sa.blog.model.Blog;
import com.sa.blog.model.Comment;
import com.sa.blog.repository.CommentRepo;
import com.sa.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private BlogService service;
    @MockBean
    private CommentRepo repo;

    @Test
    void contextLoads() {
    }

    @Test
    public void getallusersTest(){
        when(repo.findAll()).thenReturn(Stream.of(new Comment(null,"454648sfsdfr1sf","454648sfsdfr1sf","bereket",new Date(),null),
                new Comment(null,"454648sfsdfr1sf","454648sfsdfr1sf","bereket",new Date(),null),new Comment(null,"454648sfsdfr1sf","454648sfsdfr1sf","bereket",new Date(),null)).collect(Collectors.toList()));
//        assertEqual
        boolean expected=service.getAllComments().size()==2;
        assertThat(expected).isFalse();
    }
    @Test
    public void getblog(){
        BlogResponse blog= service.getBlog("6332fc25db884760ba1f9536");
        BlogResponse expected=null;
        assertThat(expected==blog).isFalse();
    }
    @Test
    public void getallBlogs(){
        List<Blog> blog= service.getAllBlogs();
        BlogResponse expected=null;
        assertThat(null == blog).isFalse();
    }
    @Test
    public void getAllComments(){
        List<Comment> comments= service.getAllComments();
        assertThat(null!=comments).isTrue();
    }

}
