package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    Task getOneByProject_ProjectIdAndParentTask_TaskIdIsNull(Long projectId);
    List<Task> findAllByProject_ProjectId(Long projectId);
}
