package com.sa.solution.service;

import com.sa.solution.SolResponse;
import com.sa.solution.model.Solution;
import com.sa.solution.repository.SolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionService {
    @Autowired
    private SolutionRepo repo;
    @Autowired
    private MongoTemplate template;
    public Solution add(Solution solution){
        return repo.save(solution);
    }

    public Boolean vote( String q_id, String voter_id){
        Solution solution=repo.findById(q_id).orElse(null);
        System.out.println(q_id);
        boolean status=false;
        List<String> accounts=new ArrayList<>();
        if(solution!=null){
            if(solution.getUpvote()!=null)
                accounts=solution.getUpvote();
            if(!accounts.contains(voter_id)){
                status=true;
                accounts.add(voter_id);
            }else
                accounts.remove(voter_id);

        }
        solution.setUpvote(accounts);
        repo.save(solution);
        return status;
    }


    public SolResponse getAll(String id) {
        SolResponse response=new SolResponse();
        Query query=new Query();
        query.addCriteria(Criteria.where("question_id").is(id));
        List<Solution> solutions=template.find(query,Solution.class);
        response.setQuestion(solutions);
        return response;
    }
}
