package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.entity.ToolProjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolProjectDetailRepo extends JpaRepository<ToolProjectDetail,Long> {
    List<ToolProjectDetail> findAllByTool_ToolId(Long toolId);
    List<ToolProjectDetail> findAllByProject_ProjectId(Long projectId);
}
