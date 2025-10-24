package com.example.shoppinglist;

import com.example.shoppinglist.domain.Shopping;
import com.example.shoppinglist.domain.ShoppingRepository;
import com.example.shoppinglist.domain.User;
import com.example.shoppinglist.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShoppinglistApplication {

    public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ShoppingRepository shoppingRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			User user = new User("user", passwordEncoder.encode("user"), "USER");
			userRepository.save(user);

			shoppingRepository.save(new Shopping("사과", 100, user));
			shoppingRepository.save(new Shopping("바나나", 10, user));
			shoppingRepository.save(new Shopping("귤", 50, user));
			shoppingRepository.save(new Shopping("과자", 17, user));
		};
	}
}
