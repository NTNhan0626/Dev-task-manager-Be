package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Issues;
import com.haku.devtask_manager.entity.ToolIssuesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolIssuesDetailRepo extends JpaRepository<ToolIssuesDetail,Long> {
    List<ToolIssuesDetail> findAllByIssues_IssuesId(Long issuesId);

}
