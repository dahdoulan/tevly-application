package org.group15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"org.group15.tveely.*"})
@EntityScan(basePackages = "org.group15.tveely.*")
@EnableJpaRepositories(basePackages = "org.group15.tveely.*")
@SpringBootApplication
@EnableScheduling
public class LauncherApplication {
    public static void main(String[] args) {
        SpringApplication.run(LauncherApplication.class, args);
    }
}