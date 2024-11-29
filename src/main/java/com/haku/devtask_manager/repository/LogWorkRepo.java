package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.LogWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogWorkRepo extends JpaRepository<LogWork,Long> {
    List<LogWork> findAllByTask_TaskId(Long taskId);
}
