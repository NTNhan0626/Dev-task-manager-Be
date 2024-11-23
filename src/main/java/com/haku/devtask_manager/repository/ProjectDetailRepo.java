package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.ProjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDetailRepo extends JpaRepository<ProjectDetail,Long> {
}
