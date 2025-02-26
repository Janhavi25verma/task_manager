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

    @GetMapping
    public ResponseEntity<?> getTasksByUser() {
        try{
        List<TaskEntry> tasks = taskService.getTasksByUser();
        return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not load tasks");
        }
    }


    //add task for authenticated user
    @PostMapping("/create-task")
    public ResponseEntity<?> createEntry(@RequestBody TaskEntry myTask) {
        try {
            TaskEntry saved = taskService.createTask(myTask);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found. task cannot be created");
        }
    }

    //delete task for authenticated user
    @DeleteMapping("/user/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        boolean isDeleted = taskService.deleteTask(taskId);

        if (isDeleted) {
            return ResponseEntity.ok("Task deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task not found or does not belong to the user");
        }
    }

    @PutMapping("/user/{taskId}")
    public ResponseEntity<String> updateTask(@RequestBody TaskEntry newTask, @PathVariable Long taskId){
        boolean isUpdated = taskService.updateById(taskId,newTask);
        if(isUpdated)
            return  ResponseEntity.ok().body("Task Updated");
        else
            return  ResponseEntity.badRequest().body("Cannot be updated");
    }



}
