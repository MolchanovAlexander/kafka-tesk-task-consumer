package org.example.kafkatesktaskconsumer.service.authentication.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.example.kafkatesktaskconsumer.model.User;
import org.example.kafkatesktaskconsumer.model.UserDto;
import org.example.kafkatesktaskconsumer.service.authentication.AuthenticationService;
import org.example.kafkatesktaskconsumer.user.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;


    @Override
    public User create(UserDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email: "
                    + requestDto.getEmail());
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        user.setFirst_name(requestDto.getFirst_name());
        return userRepository.save(user);
    }
}
