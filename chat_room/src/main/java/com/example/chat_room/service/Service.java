package com.example.chat_room.service;

import com.example.chat_room.model.Conversation;
import com.example.chat_room.model.Message;
import com.example.chat_room.repository.ConversationRepo;
import com.example.chat_room.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private ConversationRepo conversationRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MongoTemplate template;
    public void addConverstion(Conversation conversation){
        conversationRepo.save(conversation);
    }
    private List<Conversation> getConversation(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("members").in(id));
        return template.find(query,Conversation.class);
    }
    private List<Message> getMessage(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("con_id").is(id));
        return template.find(query,Message.class);
    }

}
