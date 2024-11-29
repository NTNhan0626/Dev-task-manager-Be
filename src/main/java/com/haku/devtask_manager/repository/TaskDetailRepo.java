package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDetailRepo  extends JpaRepository<TaskDetail,Long> {
    List<TaskDetail> findAllByTask_TaskId(Long taskId);
    List<TaskDetail> findAllByAccount_AccountId(Long accountId);
}
