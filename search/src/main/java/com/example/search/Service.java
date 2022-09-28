package com.example.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@org.springframework.stereotype.Service
public class Service {
    @Autowired
   private RestTemplate restTemplate;
    public ResponseDTO search(SearchDTO query) {
        switch (query.getCategory()){
            case Person -> {
                try{
                    return restTemplate.getForObject("http://localhost:8080/api/account/search/"+query.getQuery(),ResponseDTO.class);
                }catch (Exception e){
                    return null;

                }

            }
            case Blog -> {
                try{
                    return restTemplate.getForObject("http://localhost:8082/api/blogs/search/"+query.getQuery(),ResponseDTO.class);
                }catch (Exception e){
                    return null;
                }
            }
            case Question -> {
                try{
                    return restTemplate.getForObject("http://localhost:9090/api/question/search/"+query.getQuery(),ResponseDTO.class);
                }catch (Exception e){
                    return null;
                }
            }

        }
        return null;
    }
}
