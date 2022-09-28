package com.sa.accounts.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Accounts implements Serializable {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private List<String> followers=new ArrayList<>();
    private List<String> followings=new ArrayList<>();
    private Gender gender;
    private Address address;
    private List<Category> categories;
    private Date createdAt=new Date();

}
