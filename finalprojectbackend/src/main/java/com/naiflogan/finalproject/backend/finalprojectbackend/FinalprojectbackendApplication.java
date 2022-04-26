package com.naiflogan.finalproject.backend.finalprojectbackend;

import java.util.Map;

import com.naiflogan.finalproject.backend.finalprojectbackend.config.TerminateBean;
import com.naiflogan.finalproject.backend.finalprojectbackend.statesaver.StateSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Driver SpringBoot Application, creates Beans for PasswordEncoder and Canvas map
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FinalprojectbackendApplication {

    @Bean
    @Scope("singleton")
    Map<String, Canvas> canvases() {
		return StateSaver.loadCanvasState();
	}

	//Create bean to save canvas state when context is closed
	@Bean
	TerminateBean terminateBean() {
		return new TerminateBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(FinalprojectbackendApplication.class, args);
	}

}
