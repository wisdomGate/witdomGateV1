package com.example.question.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Question implements Serializable {
    @Id
    private String id;
    private String owner_id;
    private String content;
    private List<Category> categories;
    private List<String> upvote;
    private Date createdAt=new Date();

}
