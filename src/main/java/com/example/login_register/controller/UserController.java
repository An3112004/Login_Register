package com.example.login_register.controller;

import com.example.login_register.config.ApiResponse;
import com.example.login_register.domain.User;
import com.example.login_register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ApiResponse<User> crateUser(@RequestBody User user) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(this.userService.createUser(user));
        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>();
        response.setResult(this.userService.getAllUsers());
        return response;
    }
}
