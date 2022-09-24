package com.example.chat_room.service;

import com.example.chat_room.model.Conversation;
import com.example.chat_room.repository.ConversationRepo;
import com.example.chat_room.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private ConversationRepo conversationRepo;
    @Autowired
    private MessageRepo messageRepo;
    public void addConverstion(Conversation conversation){
        conversationRepo.save(conversation);
    }
}
