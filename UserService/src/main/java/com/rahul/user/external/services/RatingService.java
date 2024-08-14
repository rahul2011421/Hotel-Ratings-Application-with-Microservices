package com.rahul.user.external.services;

import com.rahul.user.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //create
    @PostMapping("/ratings")
    Rating createRating(Rating values);

    //update
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    //Delete
    @DeleteMapping("/ratings/{ratingId}")
    Rating deleteRating(@PathVariable String ratingId);
}
