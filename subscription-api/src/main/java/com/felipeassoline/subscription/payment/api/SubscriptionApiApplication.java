package com.felipeassoline.subscription.payment.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class SubscriptionApiApplication {
    public static void main(String... args) {
        SpringApplication.run(SubscriptionApiApplication.class, args);
    }
}
