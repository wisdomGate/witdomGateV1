package com.example.search;

import lombok.Data;

@Data
public class SearchDTO {
    private String query;
    private Category category;
}
