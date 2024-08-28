package org.example.kafkatesktaskconsumer.service.authentication;

import java.util.UUID;
import org.example.kafkatesktaskconsumer.model.UserDto;
import org.example.kafkatesktaskconsumer.model.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto create(UserDto requestDto);

    UserResponseDto findById(UUID uuid);
}
