package com.vijay.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vijay.moviecatalogservice.models.Rating;
import com.vijay.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfoService {

	@Autowired
	RestTemplate restTemplate;
	
    @HystrixCommand(fallbackMethod = "getFallbackUserRating", commandProperties = {
		    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") } )
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
	}
    
    public UserRating getFallbackUserRating(String userId) {
    	UserRating userRating = new UserRating();
    	userRating.setUserId(userId);
    	userRating.setRatings(Arrays.asList(new Rating("0",0)));
    	return userRating;
    }
}
