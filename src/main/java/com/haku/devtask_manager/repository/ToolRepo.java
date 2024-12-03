package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepo extends JpaRepository<Tool,Long> {
}
