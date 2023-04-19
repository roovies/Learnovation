package com.kh.learnovation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LearnovationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnovationApplication.class, args);
	}

}
