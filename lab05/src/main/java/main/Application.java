package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "static")
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "service")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}