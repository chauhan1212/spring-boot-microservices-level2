package com.vijay.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//server.port=8082
@SpringBootApplication
// @EnableEurekaClient is optional
@EnableEurekaClient
public class MovieInfoServiceApplication {

	// http://localhost:8082/movies/transformer
	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

}
