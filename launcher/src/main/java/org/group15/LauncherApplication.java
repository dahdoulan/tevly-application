package org.group15;

import org.group15.tveely.entities.RoleEntity;
import org.group15.tveely.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"org.group15.tveely.*"})
@EntityScan(basePackages = "org.group15.tveely.*")
@EnableJpaRepositories(basePackages = "org.group15.tveely.*")
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class LauncherApplication {
    public static void main(String[] args) {
        SpringApplication.run(LauncherApplication.class, args);
    }



    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(RoleEntity.builder().name("USER").build());
            }
            if (roleRepository.findByName("FILMMAKER").isEmpty()) {
                roleRepository.save(RoleEntity.builder().name("FILMMAKER").build());
            }
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                roleRepository.save(RoleEntity.builder().name("ADMIN").build());
            }
        };
    }

}