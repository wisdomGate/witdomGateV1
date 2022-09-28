package com.example.question.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO implements Serializable {
    private String ownerId;
    private String ownerFristName;
    private String ownerLastName;
}
