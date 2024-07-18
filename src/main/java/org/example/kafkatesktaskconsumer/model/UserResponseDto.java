package org.example.kafkatesktaskconsumer.model;

import lombok.Data;

@Data
public class UserResponseDto {

    private String id;
    private String email;
    private String password;
    private String first_name;
}
