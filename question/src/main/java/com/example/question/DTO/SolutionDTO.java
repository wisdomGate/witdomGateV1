package com.example.question.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDTO {
    private OwnerDTO owner;
    private Solution solution;
}
