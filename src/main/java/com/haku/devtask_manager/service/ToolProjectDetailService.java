package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.ToolProjectDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolProjectDetailResponse;

import java.util.List;

public interface ToolProjectDetailService {
    List<ToolProjectDetailResponse> findAllToolProjectDetails();
    List<ToolProjectDetailResponse> findAllByProject(Long projectId);
    ToolProjectDetailResponse createToolProjectDetail(ToolProjectDetailRequest toolProjectDetailRequest);
    ToolProjectDetailResponse updateToolProjectDetail(ToolProjectDetailRequest toolProjectDetailRequest,Long toolProjectDetailId);
    ToolProjectDetailResponse deleteToolProjectDetail(Long toolProjectDetailId);
}
