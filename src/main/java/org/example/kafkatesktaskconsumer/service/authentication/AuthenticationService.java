package org.example.kafkatesktaskconsumer.service.authentication;


import org.example.kafkatesktaskconsumer.model.User;
import org.example.kafkatesktaskconsumer.model.UserDto;

public interface AuthenticationService {
    User create(UserDto requestDto);
}
