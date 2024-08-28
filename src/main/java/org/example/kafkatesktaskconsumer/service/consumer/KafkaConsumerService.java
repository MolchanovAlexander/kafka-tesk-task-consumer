package org.example.kafkatesktaskconsumer.service.consumer;

import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.kafkatesktaskconsumer.config.MyKafkaProperties;
import org.example.kafkatesktaskconsumer.model.UserDto;
import org.example.kafkatesktaskconsumer.model.UserResponseDto;
import org.example.kafkatesktaskconsumer.service.authentication.AuthenticationService;
import org.example.kafkatesktaskconsumer.service.producer.KafkaProducerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final AuthenticationService authenticationService;
    private final KafkaProducerService producerService;

    @KafkaListener(topics = "mike", groupId = MyKafkaProperties.CONSUMER_GROUP_ID, containerFactory = "containerFactory")
    public void consumeMessage(UserDto userDto) {
        LocalTime time = LocalTime.now();
        log.info("UserDto from Queue: {} {}", userDto, time);
        try {
            UserResponseDto savedUser = authenticationService.create(userDto);
            log.info(savedUser);
            log.info(userDto);
            producerService.sendMessage(savedUser);
        } catch (Exception e) {
            log.error(e);
            UserResponseDto failedUser = new UserResponseDto();
            failedUser.setFailure("FAIL to save user with email: " + userDto.getEmail());
            producerService.sendMessage(failedUser);
        }
    }
}
