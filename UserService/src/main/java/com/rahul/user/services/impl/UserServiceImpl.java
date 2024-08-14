package com.rahul.user.services.impl;

import com.rahul.user.entities.Hotel;
import com.rahul.user.entities.Rating;
import com.rahul.user.entities.User;
import com.rahul.user.exceptions.ResourceNotFoundException;
import com.rahul.user.external.services.HotelService;
import com.rahul.user.repositories.UserRepository;
import com.rahul.user.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    public Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user with given id not found on server !!" + userId));

        // Fetch the ratings of the above user from RATING SERVICE
        // Use the userId parameter instead of a hardcoded value
        Rating[] ratingsOfUsers = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);

        logger.info("{} ", ratingsOfUsers);

        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUserDetails(String userId, User userDetails) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            User existingUser=user.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmailId(userDetails.getEmailId());
            existingUser.setAbout(userDetails.getAbout());
            return userRepository.save(existingUser);
        }
        else {
            throw new ResourceNotFoundException("User not found with id" + userId);
        }
    }
}
