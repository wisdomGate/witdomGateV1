package com.sa.accounts.Dto;

import com.sa.accounts.entity.Address;
import com.sa.accounts.entity.Category;
import com.sa.accounts.entity.Gender;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthenticationResponse {
    private String id;
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    private List<String> followers;
    private List<String> followings;
    private Gender gender;
    private Address address;
    private List<Category> categories;
    private LocalDateTime createdAt=LocalDateTime.now();
}
