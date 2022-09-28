package com.example.question.DTO;

import com.example.question.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO implements Serializable {
    private OwnerDTO owner;
    private Question question;
}
