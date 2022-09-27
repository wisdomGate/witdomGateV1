package com.sa.accounts;

import com.sa.accounts.entity.Accounts;
import com.sa.accounts.repository.AccountRepo;
import com.sa.accounts.service.AccountService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootTest
class Accounts2ApplicationTests {
    @Autowired
    private AccountService service;
    @MockBean
    private AccountRepo repo;

    @Test
    void contextLoads() {
    }
    private final List<Accounts> accountsList= Arrays.asList(new Accounts(null,"bereket","haileslassie","bhbbereket5@gmail.com","123",null,null,null,null,null,new Date())
            ,new Accounts(null,"nahom","negash","nahom@gmail.com","123",null,null,null,null,null,new Date()));
    @Test
    public void getallusersTest(){
        when(repo.findAll()).thenReturn(accountsList);
//        assertEqual
        boolean expected=service.getAll().size()==2;
        assertThat(expected).isTrue();
    }
    @Test
    public void authenticate(){
        Accounts accounts=service.authenticate("bhbbereket5@gmail.com","123");
        Boolean expected=accounts!=null;
        assertThat(expected).isTrue();
    }
    @Test
    public void getnumberofFollowers(){
        int x=service.getnumberofFollowers("6332fc25db884760ba1f9536");
        Boolean expected= x==0;
        assertThat(expected).isTrue();
    }



}
