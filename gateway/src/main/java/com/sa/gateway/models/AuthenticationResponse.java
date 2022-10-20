package com.sa.gateway.models;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Date createdAt;
}
