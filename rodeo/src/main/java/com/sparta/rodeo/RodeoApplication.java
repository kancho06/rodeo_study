package com.sparta.rodeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RodeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RodeoApplication.class, args);
    }

}
