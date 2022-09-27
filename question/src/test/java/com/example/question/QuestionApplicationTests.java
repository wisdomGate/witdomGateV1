package com.example.question;

import com.example.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class QuestionApplicationTests {
    @Autowired
    private QuestionService service;

    @Test
    void contextLoads() {
    }
    @Test
    public void voteForQuestion(){
        assertThat(service.vote("46546dgdsg12fgg","15gdgt56987sfv")).isFalse();
    }
    @Test
    public void getAllQuestion(){
        assertThat(null==service.getAllQuestions()).isTrue();
    }
    @Test
    public void getAllSolutions(){
        assertThat(null==service.getAllQuestions()).isTrue();
    }



}
