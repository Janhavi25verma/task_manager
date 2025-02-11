package com.learnerProject.TaskManager.mapper;

import com.learnerProject.TaskManager.dto.UserDto;
import com.learnerProject.TaskManager.entity.User;

public class UserMapper {

    public  static UserDto mapToUserDto(User user){
      //maps JPA entity to DTO
    return new UserDto(
            user.getUserId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getRoles(),
            user.getTasks()
    );
  }

    public static User mapToUser(UserDto userDto){
        //map DTO to JPA
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRoles(),
                userDto.getTasks()
        );
    }
}
