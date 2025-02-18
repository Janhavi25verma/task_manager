package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.dto.UserDto;
import com.learnerProject.TaskManager.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);
    Optional<User> getUserById(Long userId);
    List<User> getAllUserList();
    void updateUser(UserDto userDto);
    boolean deleteUser();
    UserDto createAdmin(UserDto userDto);

}
