package com.example.playerservice;

import com.example.playerservice.models.Player;
import com.example.playerservice.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PlayerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerServiceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(PlayerRepository repository) {
//		return args -> {
//
//			Player restaurant = new Player(
//					"Piet De Bakker",
//					"test@test.nl",
//					new ArrayList<String>(Arrays.asList("633ff78427424b07dbfbe2f5"))
//			);
//			repository.insert(restaurant);
//		};
//	}
}
