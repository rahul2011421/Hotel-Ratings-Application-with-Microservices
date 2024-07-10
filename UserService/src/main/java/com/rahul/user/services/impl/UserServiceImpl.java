package com.rahul.user.services.impl;

import com.rahul.user.entities.User;
import com.rahul.user.exceptions.ResourceNotFoundException;
import com.rahul.user.repositories.UserRepository;
import com.rahul.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;
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

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id not found on server !!"+userId));
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
