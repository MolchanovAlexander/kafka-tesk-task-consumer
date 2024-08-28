package org.example.kafkatesktaskconsumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties("mykafka")
@Data
public class MyKafkaProperties {

    public static final String CONSUMER_GROUP_ID = "mikesconsumergroup";
    private String bootstrapAddress;
}
