package com.vijay.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//server.port=8081
@SpringBootApplication
//@EnableEurekaClient is optional
@EnableEurekaClient
@EnableCircuitBreaker
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
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
