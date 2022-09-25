package com.sa.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sa.solution.model.Solution;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolResponse {
    private List<Solution> solutions;
}
