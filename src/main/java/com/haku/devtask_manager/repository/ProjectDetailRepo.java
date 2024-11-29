package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.ProjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDetailRepo extends JpaRepository<ProjectDetail,Long> {
    boolean existsByProject_ProjectId(Long projectId);
    List<ProjectDetail> findAllByProject_ProjectId(Long projectId);
    List<ProjectDetail> findAllByAccount_accountId(Long accountId);


}
