package com.example.chat_room;

import com.example.chat_room.model.Conversation;
import com.example.chat_room.repository.ConversationRepo;
import com.example.chat_room.repository.MessageRepo;
import com.example.chat_room.service.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChatRoomApplicationTests {
    @Autowired
    private Service service;
    @MockBean
    private MessageRepo repo;
    @Autowired
    private ConversationRepo conversationRepo;

    @Test
    void contextLoads() {
    }
    @Test
    public void getAllConversation(){
        List<Conversation> conversations= conversationRepo.findAll();

        assertThat(0==conversations.size()).isTrue();
    }


}
