package com.cws.user.service.service.Impl;

import com.cws.user.service.Repository.UserRepository;
import com.cws.user.service.entities.Hotel;
import com.cws.user.service.entities.Rating;
import com.cws.user.service.entities.User;
import com.cws.user.service.exception.ResourceNotFoundException;
import com.cws.user.service.external.externalService.HotelService;
import com.cws.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;


    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found no server !!" + userId));
        //fetch rating of the above user from RATING SERVICE
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
  //http://localhost:8082/hotel/8004e129-1ddd-4d40-b15f-ac59cd37cbbf
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel body = hotelService.getHotel(rating.getHotelId());
            //Hotel body = forEntity.getBody();
           // logger.info("response status code : {}",forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(body);
            //return the rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
