package com.example.question.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    private String id;
    private String content;
    private String question_id;
    private String owner_id;
    private LocalDateTime createdAt=LocalDateTime.now();
    private List<String> upvote;
}
