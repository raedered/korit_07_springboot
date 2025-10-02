package com.example.todolist;

import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			TodolistApplication.class
	);

	private final TodoRepository repository;
	private final AppUserRepository userRepository;

    public TodolistApplication(TodoRepository repository, AppUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new AppUser("user",
				"$2a$12$Zhe5trBXP4bhhPQRinU7Pu8NszbB1.y7Zb6F1jBEa0TOYbn8mTPPO",
				"USER"));

		userRepository.save(new AppUser("admin",
				"$2a$12$DtAiFItVOctzv0sRjYvbJOjZMT7T.eFUgcLaZ/3W5XPsE1D3W8k5S",
				"ADMIN"));
	}
}
