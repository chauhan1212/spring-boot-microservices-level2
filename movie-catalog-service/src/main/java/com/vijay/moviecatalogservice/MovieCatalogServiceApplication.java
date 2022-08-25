package com.vijay.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//server.port=8081
@SpringBootApplication
//@EnableEurekaClient is optional
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard // This will enable Histrix Dashboard
public class MovieCatalogServiceApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	};
	
	/* Problem : What if a microservice slow?
	 * Solution 1: Setting timeouts on Sprint RestTemplate (Not recommended)
	 * 
	 * public RestTemplate getTemplateForTimeout() {
	 * HttpComponentsClientHttpRequestFactory factory = new
	 * HttpComponentsClientHttpRequestFactory(); factory.setConnectTimeout(3000);
	 * return new RestTemplate(factory); }
	 */

	//API: http://localhost:8081/catalog/123
	/*
	 * http://localhost:8081/catalog/1234
	 * 
	 * http://localhost:8082/movies/200
	 * 
	 * http://localhost:8083/ratingsdata/users/1234
	 * 
	 * http://localhost:8083/ratingsdata/1234
	 * 
	 * //Eureka Dashboard 
	 * http://localhost:8761
	 * 
	 * // Hystrix Dashboard 
	 * http://localhost:8081/hystrix
	 * 
	 * put below url in hystrix dashboard
	 * http://localhost:8081/actuator/hystrix.stream
	 *
	 * https://api.themoviedb.org/3/movie/200?api_key=feb6f0eeaa0a72662967d77079850353
	 */
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
