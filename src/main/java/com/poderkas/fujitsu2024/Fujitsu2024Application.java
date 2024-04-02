package com.poderkas.fujitsu2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Fujitsu2024Application {

    public static void main(String[] args) {
        SpringApplication.run(Fujitsu2024Application.class, args);
    }



}
