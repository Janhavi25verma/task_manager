package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.entity.TaskEntry;
import com.learnerProject.TaskManager.entity.User;
import com.learnerProject.TaskManager.repository.TaskEntryRepository;
import com.learnerProject.TaskManager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskEntryRepository taskEntryRepository;
    @Autowired
    private UserRepository userRepository;


    public List<TaskEntry> getTasksByUserId(Long userId) {

        return taskEntryRepository.findByUser_UserId(userId);
    }


    @Override
    public TaskEntry createTask(Long userId, TaskEntry task) {
       Optional<User> user = userRepository.findById(userId);

       if(user.isEmpty()){
           return null;
       }
       if(task.getDate() == null){
           task.setDate(LocalDate.now());
       }
       task.setUser(user.get());
       return taskEntryRepository.save(task);
    }

    @Override


    @Transactional
    public boolean deleteTask(Long userId, Long taskId) {
        // Fetch task only if it belongs to the given user
        Optional<TaskEntry> task = taskEntryRepository.findByTaskIdAndUser_UserId(taskId, userId);

        if (task.isPresent()) {
            taskEntryRepository.delete(task.get());
            return true;
        }
        return false;
    }


}
