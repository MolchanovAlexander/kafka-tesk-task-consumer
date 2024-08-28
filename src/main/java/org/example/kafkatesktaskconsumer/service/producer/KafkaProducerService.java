package org.example.kafkatesktaskconsumer.service.producer;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkatesktaskconsumer.model.UserResponseDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, UserResponseDto> kafkaTemplate;

    public void sendMessage(UserResponseDto response) {
        CompletableFuture<SendResult<String, UserResponseDto>> hvedya = kafkaTemplate.send("hvedya", response);
        hvedya.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("ERROR Kafka error happened", ex);
            } else {
                log.info("SUCCESS! Response is: {}", result);
            }
        });
        log.info("Produce Response - END {} {}", LocalTime.now(), response);
    }
}
