package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class for the Spring Boot application.
 * @author  Bartosz Pałucki
 * @version 5.1
 */
@SpringBootApplication
@ComponentScan(basePackages = {"controller", "static" , "service", "config"})
public class Application {
    /**
     * Main method to run the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}