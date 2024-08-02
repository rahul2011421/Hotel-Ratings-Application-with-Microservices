package com.rahul.rating.services;

import com.rahul.rating.entities.Rating;


import java.util.List;

public interface RatingService {
    //create rating
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getallRatings();

    //get all rating by userId
    List<Rating> getRatingByUserId(String userId);

    //get all rating by hotelId
    List<Rating> getRatingByHotelId(String hotelId);

}
