package com.sa.solution;

import com.sa.solution.model.Solution;
import com.sa.solution.service.SolutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SolutionApplicationTests {
    @Autowired
    private SolutionService service;

    @Test
    void contextLoads() {
    }
    @Test
    public void VoteForSolution(){
        Solution solution=new Solution();
        assertThat(service.vote("46546365746dfsd","4464fdsf464trfv")).isFalse();
    }
    @Test
    public void getAllSolutions(){
        assertThat(null==service.getAll("3466vsfsd74dfdf")).isFalse();
    }

}
