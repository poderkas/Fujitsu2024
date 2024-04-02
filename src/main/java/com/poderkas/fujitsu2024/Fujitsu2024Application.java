package com.poderkas.fujitsu2024;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for Fujitsu2024",
                title = "OpenApi specification"
        ),
        servers = @Server(
                description = "Localhost",
                url = "http://Localhost:8080"
        )
)
@SpringBootApplication
@EnableScheduling
public class Fujitsu2024Application {

    public static void main(String[] args) {
        SpringApplication.run(Fujitsu2024Application.class, args);
    }



}
