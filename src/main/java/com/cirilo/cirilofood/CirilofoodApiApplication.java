package com.cirilo.cirilofood;

import com.cirilo.cirilofood.infrastructure.repository.CustomJpaRepositoryImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class CirilofoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirilofoodApiApplication.class, args);
	}

}
