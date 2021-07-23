package com.cirilo.cirilofood;

import com.cirilo.cirilofood.infrastructure.repository.CustomJpaRepositoryImpl;

import org.apache.commons.lang3.concurrent.TimedSemaphore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class CirilofoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(CirilofoodApiApplication.class, args);
	}

}
