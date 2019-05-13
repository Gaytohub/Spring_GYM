package com.springboot.gymclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableCaching
@SpringBootApplication
@EnableJpaAuditing
//@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class GymclubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymclubApplication.class, args);
    }

}
