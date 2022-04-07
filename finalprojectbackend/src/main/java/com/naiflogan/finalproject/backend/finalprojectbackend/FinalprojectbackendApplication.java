package com.naiflogan.finalproject.backend.finalprojectbackend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalprojectbackendApplication {


	@Bean
	Map<String, Canvas> canvases() {
		return new HashMap<String, Canvas>();
	}
	public static void main(String[] args) {
		SpringApplication.run(FinalprojectbackendApplication.class, args);
	}

}
