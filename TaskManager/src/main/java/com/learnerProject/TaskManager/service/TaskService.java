package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.entity.TaskEntry;

import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<TaskEntry> getTasksByUserId(Long userId);

  TaskEntry createTask (Long userId, TaskEntry task);

  boolean deleteTask (Long userId, Long taskId);
}
