package com.example.chat_room.service;

import com.example.chat_room.model.Conversation;
import com.example.chat_room.model.Message;
import com.example.chat_room.repository.ConversationRepo;
import com.example.chat_room.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private ConversationRepo conversationRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MongoTemplate template;
    public Conversation addConverstion(Conversation conversation){
       return conversationRepo.save(conversation);
    }
    public List<Conversation> getConversation(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("members").in(id));
        return template.find(query,Conversation.class);
    }
    public List<Message> getMessage(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("con_id").is(id));
        return template.find(query,Message.class);
    }
    public void deleteConversation(String user_id,String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("members").in(Arrays.asList(user_id,id)));
        Conversation conversation=template.findOne(query,Conversation.class);
        assert conversation != null;
        conversationRepo.delete(conversation);
        query=new Query();
        query.addCriteria(Criteria.where("con_id").is(conversation.getId()));
        template.remove(query,Message.class);
    }
    public Message addMessage(Message message) {
        return messageRepo.save(message);
    }
}
