package com.example.chat_room.repository;

import com.example.chat_room.model.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepo extends MongoRepository<Conversation, String> {
//    @Query(value = "")
//    List<Conversation> findBySender(String id);
}
