package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main class for the Spring Boot application.
 * @author  Bartosz Pałucki
 * @version 5.1
 */
@SpringBootApplication(scanBasePackages = {"controller", "static" , "model", "service", "repository"})
@ComponentScan(basePackages = {"controller", "static" , "model", "service", "repository"})
@EntityScan("model")
@EnableJpaRepositories("repository")
public class Application {
    /**
     * Main method to run the Spring Boot application.
     * @param args  Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}