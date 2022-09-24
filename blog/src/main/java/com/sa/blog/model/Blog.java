package com.sa.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Blog {
    @Id
    private String id;
    private String owner_id;
    private String title;
    private String content;
    private LocalDateTime createdAt=LocalDateTime.now();
}
