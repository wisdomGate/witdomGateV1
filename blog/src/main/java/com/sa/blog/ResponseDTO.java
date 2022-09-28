package com.sa.blog;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseDTO implements Serializable {
    List<Object> response;
}
