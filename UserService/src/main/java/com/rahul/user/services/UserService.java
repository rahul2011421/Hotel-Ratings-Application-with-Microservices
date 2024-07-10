package com.rahul.user.services;

import com.rahul.user.entities.User;

import java.util.List;

public interface UserService {
    //create user
    User saveUser(User user);
    List<User> getAllUser();
    User getUserById(String userId);
    void deleteUser(String userId);
    User updateUserDetails(String userId, User userDetails);
}
