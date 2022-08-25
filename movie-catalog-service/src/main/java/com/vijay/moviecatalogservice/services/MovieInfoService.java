package com.vijay.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vijay.moviecatalogservice.models.CatalogItem;
import com.vijay.moviecatalogservice.models.Movie;
import com.vijay.moviecatalogservice.models.Rating;

@Service
public class MovieInfoService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
			threadPoolKey = "catelogItemPool" , // theadPoolKey will create pool for allow 
			threadPoolProperties =  {
    @HystrixProperty(name = "coreSize", value = "10"), // number of threads allow at a same time
    @HystrixProperty(name = "maxQueueSize",value = "5" )} ) // number of threads will be on queue
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

    public CatalogItem getFallbackCatalogItem(Rating rating) {
    	return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}