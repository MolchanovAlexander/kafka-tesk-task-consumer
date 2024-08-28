package org.example.kafkatesktaskconsumer.model;

import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {

    private UUID id;
    private String email;
    private String password;
    private String first_name;
}
