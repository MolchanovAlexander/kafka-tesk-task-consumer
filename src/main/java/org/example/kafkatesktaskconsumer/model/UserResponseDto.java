package org.example.kafkatesktaskconsumer.model;

import java.util.UUID;
import lombok.Data;

@Data
public class UserResponseDto {
    private UUID id;
    private String email;
    private String password;
    private String first_name;
    private String failure;
}
