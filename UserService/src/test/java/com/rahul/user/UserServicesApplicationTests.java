package com.rahul.user;

import com.rahul.user.entities.Rating;
import com.rahul.user.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServicesApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
//	@Test
//	void createRating(){
//		Rating rating = Rating.builder().rating(9).userId("").hotelId("").feedback("maharaja hotel provides good food checked through feign client").build();
//		Rating savedRating = ratingService.createRating(rating);
//		System.out.println("new rating created");
//	}

}
