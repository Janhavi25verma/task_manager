package com.learnerProject.TaskManager.dto;


import com.learnerProject.TaskManager.entity.TaskEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    //to transfer data between client and server
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> roles;
    private List<TaskEntry> tasks;

}
