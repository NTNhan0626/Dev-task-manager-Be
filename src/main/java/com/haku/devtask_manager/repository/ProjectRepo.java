package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
