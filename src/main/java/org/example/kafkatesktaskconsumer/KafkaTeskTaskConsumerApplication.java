package org.example.kafkatesktaskconsumer;

import org.example.kafkatesktaskconsumer.config.MyKafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = MyKafkaProperties.class)
public class KafkaTeskTaskConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTeskTaskConsumerApplication.class, args);
    }

}
