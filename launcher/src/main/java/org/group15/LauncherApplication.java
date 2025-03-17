package org.group15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"org.group15.tveely.*"})
@EntityScan(basePackages = "org.group15.tveely.entities.*")
@SpringBootApplication
public class LauncherApplication {
    public static void main(String[] args) {
        SpringApplication.run(LauncherApplication.class, args);
    }
}