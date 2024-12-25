package com.tutorial.config_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
	}

}
// https://www.youtube.com/watch?v=AfOrvGKIk2I&list=PL4bT56Uw3S4yTSw5Cg1-mhgoS85fVeFkT&index=5

