package com.cws.rating.RatingService.service;

import com.cws.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create rating
    Rating createRating(Rating rating);

    //Get All Rating
    List<Rating> getAllRating();

    //Get All By UserId
    List<Rating> getAllRatingByUserId(String UserId);

    //Get All by Hotel

    List<Rating> getRatingByHotelId(String hotelId);

}
