package com.example.question.service;

import com.example.question.DTO.*;
import com.example.question.ResponseDTO;
import com.example.question.model.Question;
import com.example.question.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo repo;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private RestTemplate restTemplate;
    public Question add(Question question){
        return repo.save(question);
    }

    public Boolean vote( String q_id, String voter_id){
        Question question=repo.findById(q_id).orElse(null);
        System.out.println(q_id);
        boolean status=false;
        List<String> accounts=new ArrayList<>();
        if(question!=null){
            if(question.getUpvote()!=null)
                accounts=question.getUpvote();
            if(!accounts.contains(voter_id)){
                status=true;
                accounts.add(voter_id);
            }else
                accounts.remove(voter_id);
            question.setUpvote(accounts);
            repo.save(question);
        }

        return status;
    }

    public List<QuestionDTO> getAllQuestions() {
        try{
        List<Question> questions=repo.findAll();
        List<QuestionDTO> dtos=new ArrayList<>();
        int i=0;
        for(Question q:questions){
            OwnerDTO ownerDTO=restTemplate.getForObject("http://localhost:8080/api/account/getOwner/"+q.getOwner_id(),OwnerDTO.class);
            QuestionDTO dto=new QuestionDTO();
            //System.out.println(ownerDTO.getOwner_fristName());
            dto.setOwner(ownerDTO);
            dto.setQuestion(q);
            dtos.add(dto);
            i++;
            if(i==50)
                break;
        }
        return dtos;}catch (Exception e){
            return null;
        }
    }

    public List<SolutionDTO> getsolution(String id){
        //System.out.println(id);
        try{
        SolResponse solutions= restTemplate.getForObject("http://localhost:8081/getSolution/"+id,SolResponse.class);
        List<SolutionDTO> dtos=new ArrayList<>();
        int i=0;
        assert solutions != null;
        for(Solution s:solutions.getQuestion()){
            OwnerDTO ownerDTO=restTemplate.getForObject("http://localhost:8080/api/account/getOwner/"+s.getOwner_id(),OwnerDTO.class);
            SolutionDTO dto=new SolutionDTO();
            dto.setOwner(ownerDTO);
            dto.setQuestion(s);
            dtos.add(dto);
            if(i==20)
                break;
            i++;
        }
        System.out.println(dtos.get(0).getQuestion().getContent());
        return dtos;}catch (Exception e){
            return null;
        }
    }

    public ResponseEntity<Solution> addSolution(Solution solution) {
        try{
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Solution> requestEntity =
                new HttpEntity<>(solution, headers);
        ResponseEntity<Solution> str;
        str = restTemplate.postForEntity("http://localhost:8081/add",requestEntity,Solution.class);
        return str;}catch (Exception e){
            return null;
        }
    }

    public Boolean addsolutionVote(String id, String v_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Solution> requestEntity =
                new HttpEntity<>(null, headers);
        try {
            Boolean aBoolean = restTemplate.postForObject("http://localhost:8081/vote/" + id + "/" + v_id, requestEntity, Boolean.class);
            return aBoolean;
        }catch (Exception e){
            return false;
        }


    }

    public List<QuestionDTO> personal(String id) {
        try{
        Query query=new Query();
        query.addCriteria(Criteria.where("owner_id").is(id));
        List<Question> questions=template.find(query,Question.class);
        List<QuestionDTO> dtos=new ArrayList<>();
        int i=0;
        for(Question q:questions){
            OwnerDTO ownerDTO=restTemplate.getForObject("http://localhost:8080/api/account/getOwner/"+id,OwnerDTO.class);
            QuestionDTO dto=new QuestionDTO();
            //System.out.println(ownerDTO.getOwner_fristName());
            dto.setOwner(ownerDTO);
            dto.setQuestion(q);
            dtos.add(dto);
            i++;
            if(i==50)
                break;
        }
        return dtos;}catch (Exception e){
            return null;
        }
    }
    public ResponseDTO search(String str) {
        Criteria criteria=new Criteria();
        criteria.orOperator(Criteria.where("content").regex(str));
        Query query=new Query(criteria);
        List<Question> questions=template.find(query,Question.class);
        List<QuestionDTO> dtos=new ArrayList<>();
        int i=0;
        for(Question q:questions){
            OwnerDTO ownerDTO=restTemplate.getForObject("http://localhost:8080/api/account/getOwner/"+q.getOwner_id(),OwnerDTO.class);
            QuestionDTO dto=new QuestionDTO();
            //System.out.println(ownerDTO.getOwner_fristName());
            dto.setOwner(ownerDTO);
            dto.setQuestion(q);
            dtos.add(dto);
            i++;
            if(i==50)
                break;
        }
        ResponseDTO dto=new ResponseDTO();
        dto.setResponse(Collections.singletonList(dtos));
        return dto;
    }
}
