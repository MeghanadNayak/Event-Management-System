package com.event;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.event.beans.User;
import com.event.repository.UserRepository;

@SpringBootApplication
public class EventManagementSystemSpringBootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementSystemSpringBootApiApplication.class, args);
		}
	

	@Bean
    CommandLineRunner initAdmin(UserRepository userRepository) {
        return args -> {
           
            if (userRepository.getUserByEmail("admin@event.com") == null) {
                User admin = new User();
                admin.setName("System Admin");
                admin.setEmail("admin@event.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println(" Default Admin Created!");
            }
        };
      }
	}
