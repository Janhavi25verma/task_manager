package com.learnerProject.TaskManager.repository;

import com.learnerProject.TaskManager.entity.TaskEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskEntryRepository extends JpaRepository<TaskEntry,Long> {
    List<TaskEntry> findByUser_UserId(Long userId);
    Optional<TaskEntry> findByTaskIdAndUser_UserId(Long taskId, Long userId);
}
