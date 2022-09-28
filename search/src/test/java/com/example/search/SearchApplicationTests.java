package com.example.search;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SearchApplicationTests {
    @Autowired
    private Service service;
    private final SearchDTO searchDTO=new SearchDTO();
    @Test
    void contextLoads() {
    }
    @Test
    public void SearchWithWrongCategory(){
        searchDTO.setCategory(Category.valueOf("Mankind"));
        searchDTO.setQuery("bereket");
        assertThat(null==service.search(searchDTO)).isTrue();
    }
    @Test
    public void SearchWithWrongQuetionContent(){
        searchDTO.setCategory(Category.valueOf("Question"));
        searchDTO.setQuery("");
        assertThat(null==service.search(searchDTO)).isTrue();
    }
    @Test
    public void SearchWithWrongBlogTitle(){
        searchDTO.setCategory(Category.valueOf("Blog"));
        searchDTO.setQuery("");
        assertThat(null==service.search(searchDTO)).isTrue();
    }

}
