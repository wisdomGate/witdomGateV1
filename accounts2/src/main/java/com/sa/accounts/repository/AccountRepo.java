package com.sa.accounts.repository;

import com.sa.accounts.entity.Accounts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends MongoRepository<Accounts,String> {
    public static final String HASH_KEY = "Accounts";
    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
//    List<Accounts> findAll();
    Optional<Accounts> findAccountsByEmail(String email);
    Accounts findAccountsByFirstName(String str);
    @Query(value = "{$or: [{'firstName':?1},{'lastName': ?1}]}")
    List<Accounts> findBySearch(String str);
//    @Query(value = "{'_id=?0': {$push}}")
//    void findAccountsById(String follower, String followed);
}
