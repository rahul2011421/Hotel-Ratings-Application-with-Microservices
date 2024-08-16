package com.rahul.user.controllers;


import com.rahul.user.entities.User;
import com.rahul.user.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    public Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Creating fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because serviec is down : ", ex.getMessage());
        User user = User.builder()
                .emailId("dummy@gmail.com")
                .username("dummy")
                .about("created dummy user because some of services are down")
                .userId("12345").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

   @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
       List<User> allUser = userService.getAllUser();
       return ResponseEntity.ok(allUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId){
        userService.deleteUser(userId);
    }
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId,@RequestBody User userDetails){
        return userService.updateUserDetails(userId,userDetails);
    }
}
