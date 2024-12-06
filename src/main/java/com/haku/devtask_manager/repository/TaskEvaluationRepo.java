package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.TaskEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEvaluationRepo extends JpaRepository<TaskEvaluation,Long> {
    TaskEvaluation findOneByTask_TaskId(Long taskId);
}
