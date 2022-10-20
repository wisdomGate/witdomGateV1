package com.example.chat_room.controller;

import com.example.chat_room.model.Conversation;
import com.example.chat_room.model.Message;
import com.example.chat_room.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
public class Controller {
    @Autowired
    private Service service;

    @PostMapping("/getallConversation/{user_id}")
    public List<Conversation> getAll(@PathVariable String user_id){
        return service.getConversation(user_id);
    }
    @GetMapping("/getMessages/{conv_id}")
    public List<Message> getAllMessages(@PathVariable String conv_id){
        return service.getMessage(conv_id);
    }
    @PostMapping("/addConvesrsation")
    public Conversation addConversation(@RequestBody Conversation conversation){
         return service.addConverstion(conversation);
    }
    @PostMapping("/addMessage")
    public Message addMessage(@RequestBody Message message){
        return service.addMessage(message);
    }
    @DeleteMapping("/delete/{user_id}/{id}")
    public void deleteConversation(@PathVariable String user_id,@PathVariable String id){
        service.deleteConversation(user_id,id);
    }

}
