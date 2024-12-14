package com.sparta.logistics.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InfraApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraApplication.class, args);
	}

}
