package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.entity.TaskEntry;
import com.learnerProject.TaskManager.entity.User;
import com.learnerProject.TaskManager.repository.TaskEntryRepository;
import com.learnerProject.TaskManager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

   //Get task for authenticated user
    public List<TaskEntry> getTasksByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return taskEntryRepository.findByUser_Email(email);
    }

    @Transactional
    @Override
    public boolean updateById(Long taskId, TaskEntry newTask) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        TaskEntry existingTask = taskEntryRepository.findByTaskIdAndUser_Email(taskId, email)
                .orElseThrow(() -> new RuntimeException("Task not found for the user"));

        existingTask.setTaskCategory(newTask.getTaskCategory());
        existingTask.setTaskDescription(newTask.getTaskDescription());

        taskEntryRepository.save(existingTask);
        return true;
    }


    @Override
    public TaskEntry createTask(TaskEntry task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
       Optional<User> user = userRepository.findByEmail(email);

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
    public boolean deleteTask(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Fetch task only if it belongs to the given user
        Optional<TaskEntry> task = taskEntryRepository.findByTaskIdAndUser_Email(taskId, email);

        if (task.isPresent()) {
            taskEntryRepository.delete(task.get());
            return true;
        }
        return false;
    }


}
