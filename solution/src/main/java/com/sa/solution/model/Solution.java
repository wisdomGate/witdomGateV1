package com.sa.solution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Solution {
    @Id
    private String id;
    private String content;
    private String question_id;
    private String owner_id;
    private LocalDateTime createdAt=LocalDateTime.now();
    private List<String> upvote;

}
