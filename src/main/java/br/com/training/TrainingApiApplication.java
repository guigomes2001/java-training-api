package br.com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.training.controller, br.com.training.repository")
public class TrainingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApiApplication.class, args);
	}

}
