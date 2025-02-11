package com.learnerProject.TaskManager.controller;

import com.learnerProject.TaskManager.entity.TaskEntry;
import com.learnerProject.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskEntry>> getTasksByUser(@PathVariable Long userId) {
        List<TaskEntry> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createEntry(@PathVariable Long userId, @RequestBody TaskEntry myTask) {
        try {
            TaskEntry saved = taskService.createTask(userId, myTask);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found. task cannot be created");
        }
    }

    //    @DeleteMapping("/delete/{taskId}")
//    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
//        try {
//            taskService.deleteTask(taskId);
//            return ResponseEntity.ok().body("task deleted");
//        } catch (Exception e) {
//            {
//                return ResponseEntity.badRequest().body("could not delete");
//            }
//        }
//    }
    @DeleteMapping("/user/{userId}/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        boolean isDeleted = taskService.deleteTask(userId, taskId);

        if (isDeleted) {
            return ResponseEntity.ok("Task deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task not found or does not belong to the user");
        }
    }



}
