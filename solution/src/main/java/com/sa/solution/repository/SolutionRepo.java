package com.sa.solution.repository;

import com.sa.solution.model.Solution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepo extends MongoRepository<Solution,String> {
}
