package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepo extends JpaRepository<Issues,Long> {
    List<Issues> findAllByTask_TaskId(Long taskId);
    Issues findTopByOrderByIssuesIdDesc();
}
