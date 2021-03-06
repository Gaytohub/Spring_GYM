package com.springboot.gymclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class GymclubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymclubApplication.class, args);
    }

}
