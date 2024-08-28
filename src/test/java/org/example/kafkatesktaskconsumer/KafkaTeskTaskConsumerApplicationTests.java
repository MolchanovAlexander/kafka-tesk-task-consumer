package org.example.kafkatesktaskconsumer;

import java.util.UUID;
import org.example.kafkatesktaskconsumer.model.UserResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("kafka")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///db?TC_INITSCRIPT=sql/schema.sql"
})
@Testcontainers
class KafkaTeskTaskConsumerApplicationTests {

    //static KafkaContainer kafka;
    @Container
    static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.2.1"));

    @DynamicPropertySource
    static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }
    @Autowired
    KafkaTemplate<String, UserResponseDto> kafkaTemplate;

    @BeforeAll
    public static void beforeAll() {
        //kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.2.1"));
        kafka.start();
        String bootstrapServers = kafka.getBootstrapServers();
        System.out.println("Bootstrap servers " + bootstrapServers);
        System.setProperty("kafka.bootstrapAddress", bootstrapServers);
    }

    @Test
    void testSpaceShipControllerMocked() throws InterruptedException {
        UserResponseDto testDto = new UserResponseDto();
        testDto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        testDto.setPassword("1111");
        testDto.setFirst_name("Galya");
        testDto.setEmail("ggg@gg.com");
        kafkaTemplate.send("mike", testDto);
//        kafkaTemplate.send("mike", testDto);
       kafkaTemplate.send("hvedya", testDto);
        testDto.setFailure("Error error");
        testDto.setPassword(null);
        Thread.sleep(10);
        kafkaTemplate.send("mike", testDto);
    }
}
