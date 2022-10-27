package com.jaadla.namlogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NamLogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NamLogApiApplication.class, args);
    }

}
