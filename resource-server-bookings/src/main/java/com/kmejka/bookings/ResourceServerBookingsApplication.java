package com.kmejka.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResourceServerBookingsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServerBookingsApplication.class, args);
    }
}
