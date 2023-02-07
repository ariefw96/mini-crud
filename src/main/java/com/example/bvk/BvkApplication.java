package com.example.bvk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(value = {"com.example.bvk"})
@EnableJpaRepositories("com.example.bvk.repository")
@EntityScan(basePackages = {"com.example.bvk.model.entity"})
@SpringBootApplication
public class BvkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BvkApplication.class, args);
	}

}
