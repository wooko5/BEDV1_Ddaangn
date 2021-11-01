package com.dev.ddaangn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class DdaangnApplication {



    public static void main(String[] args) {
        SpringApplication.run(DdaangnApplication.class, args);
    }

}
