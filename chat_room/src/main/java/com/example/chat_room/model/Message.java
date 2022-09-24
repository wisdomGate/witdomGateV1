package com.example.chat_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Message {
    @Id
    private String id;
    private String con_id;
    private String sender;
    private String content;
    private LocalDateTime createdAt=LocalDateTime.now();
}
