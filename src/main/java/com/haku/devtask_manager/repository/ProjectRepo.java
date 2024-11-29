package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findAllByProjectType(boolean type);
}
