package org.example.controller;

import jakarta.validation.groups.Default;
import org.example.dto.OnCreate;
import org.example.dto.OnUpdate;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.servise.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Validated(OnCreate.class) @RequestBody UserRequest request) {
        //UserResponse created = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){

        logger.info("Called getUserById: id={}", id);
        return ResponseEntity.status(HttpStatus.OK).
                body(userService.getUserById(id));

    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        logger.info("Called getAllUsers");
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
            @Validated({Default.class, OnUpdate.class}) @RequestBody UserRequest request) {
        UserResponse updated = userService.updateUser(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/test")
//    public String test() {
//        return "Spring работает!";
//    }

//    }
//
//    public UserResponse createUser(UserRequest userRequest) {
//        return userService.createUser(userRequest);
//    }
//
//    public UserResponse getUserById(UserRequest userRequest) {
//        return userService.getUserById(userRequest);
//    }
//
//    public UserResponse updateUser(UserRequest userRequest) {
//        return userService.updateUser(userRequest);
//    }
//
//    public void deleteUser(UserRequest userRequest) {
//        userService.deleteUser(userRequest);
//    }
//
//    public List<UserResponse> getAllUsers() {
//        return userService.getAllUsers();
//    }


}