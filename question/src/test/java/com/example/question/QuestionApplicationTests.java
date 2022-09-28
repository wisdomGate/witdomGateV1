package com.example.question;

import com.example.question.DTO.*;
import com.example.question.model.Category;
import com.example.question.model.Question;
import com.example.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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
        OwnerDTO ownerDTO=new OwnerDTO();
        QuestionDTO questionDTO=new QuestionDTO();
        assertThat(service.vote("46546dgdsg12fgg","15gdgt56987sfv")).isFalse();
    }
    @Test
    public void voteForSolution(){
        RequestDTO requestDTO=new RequestDTO();
        assertThat(service.addsolutionVote("46546dgdsg12fgg","15gdgt56987sfv")).isFalse();
    }
    @Test
    public void getAllQuestion(){
        assertThat(null==service.getAllQuestions()).isFalse();
    }
    @Test
    public void getAllSolutions(){
        assertThat(null==service.getsolution("464d6gdfr1gd45")).isTrue();
    }

    @Test
    public void getPersonal(){
        SolutionDTO solutionDTO=new SolutionDTO();
        SolResponse response=new SolResponse();
        assertThat(null==service.personal("1346546f464rff")).isFalse();
    }

    @Test
    public void AddSolutionVote(){
        assertThat(service.addsolutionVote("1346546f464rff","sfsdferfsf547")).isFalse();
    }

    @Test
    public void addSolution(){
         String id=null;
         String owner_id="sfsdf4fsf1v45";
         String content="jljzchkjrlsfsd";
         List<Category> categories=null;
         List<String> upvote=null;
         Date createdAt=new Date();
        Solution question=new Solution(null,content,id,owner_id,createdAt,upvote);
        assertThat(null==service.addSolution(question)).isTrue();
    }
    @Test
    public void Search(){
        assertThat(null==service.search("seytan")).isFalse();
    }




}
