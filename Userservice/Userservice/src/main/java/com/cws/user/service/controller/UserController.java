package com.cws.user.service.controller;

import com.cws.user.service.entities.User;
import com.cws.user.service.service.Impl.UserServiceImpl;
import com.cws.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    //Create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //Single user get
    int retryCount=1;
    @GetMapping("/{userId}")
   //@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Get Single User Handler:UserController");
        logger.info("Retry count: {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);

    }

    //creating fall back method for circutbreaker


    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
       logger.info("Fallback is executed because service is down :"+ex.getMessage());
       ex.printStackTrace();
        User user = User.builder()
                .userId("12345")
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This use is cresated dummy because some services are down !!")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //All user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> user=userService.getAllUser();
        return ResponseEntity.ok(user);
    }
}
