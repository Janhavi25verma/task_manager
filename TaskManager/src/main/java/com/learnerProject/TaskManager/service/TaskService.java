package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.entity.TaskEntry;

import java.util.List;

public interface TaskService {

  List<TaskEntry> getTasksByUser();

  boolean updateById(Long taskId, TaskEntry newTask);

  TaskEntry createTask (TaskEntry task);

  boolean deleteTask (Long taskId);
}
