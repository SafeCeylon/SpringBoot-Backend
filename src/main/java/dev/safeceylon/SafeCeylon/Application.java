package dev.safeceylon.SafeCeylon;

import dev.safeceylon.SafeCeylon.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner generalUser() {
		return args -> {
			log.info("Creating a new user...");
			User user = new User(
				0,
				"John Doe",
				"1234567890",
				"0712345678",
				"johndoe@gmail.com",
				"123, Main Street",
				"Colombo",
				"Colombo 07",
				"password",
				LocalDateTime.now());
			log.info("User created: " + user);
		};
	}

}
