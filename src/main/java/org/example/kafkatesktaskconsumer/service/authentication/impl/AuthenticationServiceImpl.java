package org.example.kafkatesktaskconsumer.service.authentication.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.example.kafkatesktaskconsumer.model.User;
import org.example.kafkatesktaskconsumer.model.UserDto;
import org.example.kafkatesktaskconsumer.model.UserResponseDto;
import org.example.kafkatesktaskconsumer.service.authentication.AuthenticationService;
import org.example.kafkatesktaskconsumer.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto create(UserDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email: "
                    + requestDto.getEmail());
        }
        User user = new User();
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        user.setFirst_name(requestDto.getFirst_name());
        userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        return responseDto;
    }

    @Override
    public UserResponseDto findById(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(
                ()-> new RuntimeException("There is no user with id: " + uuid)
        );
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(user.getPassword());
        responseDto.setFirst_name(user.getFirst_name());
        return responseDto;
    }
}
