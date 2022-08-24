package com.vijay.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.moviecatalogservice.models.CatalogItem;
import com.vijay.moviecatalogservice.models.UserRating;
import com.vijay.moviecatalogservice.services.MovieInfoService;
import com.vijay.moviecatalogservice.services.UserRatingInfoService;

@RestController
@RequestMapping(path = "/catalog")
public class MovieCatalogResource {

	/*
	 * http://localhost:8081/catalog/1234
	 * 
	 * http://localhost:8081/catalog/getMovieInfoServiceInstances
	 */
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	MovieInfoService movieInfo;

	@Autowired
	UserRatingInfoService userRatingInfo;


	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingInfo.getUserRating(userId);
		List<CatalogItem> catalogs = userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
		System.out.println("Catalogs passed : " + catalogs);
		return catalogs;
	}
	
	
	@RequestMapping("/getMovieInfoServiceInstances")
	public List<ServiceInstance> getAllInstances() {
		List<ServiceInstance> instances= discoveryClient.getInstances("movie-info-service");
		
		return instances;
	}
}
