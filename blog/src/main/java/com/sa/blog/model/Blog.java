package com.sa.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Blog implements Serializable {
    @Id
    private String id;
    private String owner_id;
    private String title;
    private String content;
    private Date createdAt=new Date();
}
