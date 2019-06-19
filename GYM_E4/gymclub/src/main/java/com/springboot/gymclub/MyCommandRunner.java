package com.springboot.gymclub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8080/login");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}