package com.cws.user.service;

import com.cws.user.service.entities.Rating;
import com.cws.user.service.external.externalService.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserserviceApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("love you");
	}
	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//	Rating rating= Rating.builder().rating(10).userId("").hotelId("").feedback("this is creating using fiegn client").build();
//		ResponseEntity<Rating> saveRating = ratingService.createRating(rating);
//
//
//		System.out.println("new rating created");
//	}

}
