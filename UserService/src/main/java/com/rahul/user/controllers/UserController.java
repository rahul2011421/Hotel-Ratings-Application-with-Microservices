package com.rahul.user.controllers;

import com.rahul.user.entities.User;
import com.rahul.user.services.UserService;
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

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
       User user= userService.getUserById(userId);
       return ResponseEntity.ok(user);
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
