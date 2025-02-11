package com.learnerProject.TaskManager.controller;


import com.learnerProject.TaskManager.dto.UserDto;
import com.learnerProject.TaskManager.entity.User;
import com.learnerProject.TaskManager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        try {
            userService.updateUser(userDto);
            return ResponseEntity.ok().body("Updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to update the details");
        }

    }

    @GetMapping("/task/{userId}")
    public ResponseEntity<Optional<User>> getUserWithTask(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        boolean isDeleted = userService.deleteUser();
        if(isDeleted)
            return ResponseEntity.ok().body("User Deleted");
        else
            return ResponseEntity.badRequest().body("User cannot be deleted");
    }

//    @GetMapping("/allUser")
//    public ResponseEntity<?> getAllUsers(){
//        try{
//            return new ResponseEntity<>(userService.getAllUserList(),HttpStatus.OK);
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("No user found");
//        }
//    }

}
