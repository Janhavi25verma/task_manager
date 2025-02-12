package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.dto.UserDto;
import com.learnerProject.TaskManager.entity.User;
import com.learnerProject.TaskManager.mapper.UserMapper;
import com.learnerProject.TaskManager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        if(userDto.getTasks() == null){
//            userDto.setTasks(new ArrayList<>());
//        }
//     User user = UserMapper.mapToUser(userDto);
//        User savedUser = userRepository.save(user);
//        return UserMapper.mapToUserDto(savedUser);
//    }
    @Override
    public UserDto createUser(UserDto userDto) {
        if(userDto.getTasks() == null){
            userDto.setTasks(new ArrayList<>());
        }
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }



    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUserList() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String email = authentication.getName();
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (existingUser != null) {
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

            userRepository.save(existingUser);
        }
    }

    @Override
    @Transactional
    public boolean deleteUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            userRepository.deleteByEmail((email));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserDto createAdmin(UserDto userDto) {
        if(userDto.getTasks() == null){
            userDto.setTasks(new ArrayList<>());
        }
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ADMIN"));
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

}
