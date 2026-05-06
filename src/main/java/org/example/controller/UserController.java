package org.example.controller;

import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.example.servise.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserResponse createUser(UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    public UserResponse getUserById(UserRequest userRequest) {
        return userService.getUserById(userRequest);
    }

    public UserResponse updateUser(UserRequest userRequest) {
        return userService.updateUser(userRequest);
    }

    public void deleteUser(UserRequest userRequest) {
        userService.deleteUser(userRequest);
    }

    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }


}