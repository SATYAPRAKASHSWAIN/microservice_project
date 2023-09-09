package com.cws.user.service.external.externalService;

import com.cws.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.ws.rs.Path;
import java.util.Map;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    //GET

    //POST
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating values);
    //PUT

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId")
    public void deleteRating(@PathVariable String ratingId);

}
