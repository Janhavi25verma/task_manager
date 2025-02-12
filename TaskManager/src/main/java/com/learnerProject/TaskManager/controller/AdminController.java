package com.learnerProject.TaskManager.controller;


import com.learnerProject.TaskManager.dto.UserDto;
import com.learnerProject.TaskManager.entity.User;
import com.learnerProject.TaskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> userList = userService.getAllUserList();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No user found");
        }
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserDto userDto){
        try {
            UserDto savedUser = userService.createAdmin(userDto);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cannot create admin");
        }
    }
}
