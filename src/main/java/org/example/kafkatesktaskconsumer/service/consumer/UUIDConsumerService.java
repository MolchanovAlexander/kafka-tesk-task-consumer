package org.example.kafkatesktaskconsumer.service.consumer;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.kafkatesktaskconsumer.config.MyKafkaProperties;
import org.example.kafkatesktaskconsumer.model.UserResponseDto;
import org.example.kafkatesktaskconsumer.service.authentication.AuthenticationService;
import org.example.kafkatesktaskconsumer.service.producer.KafkaProducerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UUIDConsumerService {

    private final KafkaProducerService producerService;
    private final AuthenticationService authenticationService;

    @KafkaListener(topics = "uuid_topic", groupId = MyKafkaProperties.CONSUMER_GROUP_ID, containerFactory = "uuidKafkaListenerContainerFactory")
    public void consumeUUID(UUID uuid) {
        try {
            log.info("Received UUID from Queue: {}", uuid);
            UserResponseDto responseUserDto = authenticationService.findById(uuid);
            log.info(responseUserDto);
            producerService.sendMessage(responseUserDto);
        } catch (Exception e) {
            log.error(e);
            UserResponseDto failedUser = new UserResponseDto();
            failedUser.setFailure("FAIL to find user with id: " + uuid);
            producerService.sendMessage(failedUser);
        }
    }
}
