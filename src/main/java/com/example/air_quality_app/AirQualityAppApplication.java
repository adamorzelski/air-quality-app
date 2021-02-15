package com.example.air_quality_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AirQualityAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(AirQualityAppApplication.class, args);
    }

}
