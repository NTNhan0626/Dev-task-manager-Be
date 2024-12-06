package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.ToolIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolIssuesDetailResponse;

import java.util.List;

public interface ToolIssuesDetailService {
    List<ToolIssuesDetailResponse> createToolIssuesDetail (List<ToolIssuesDetailRequest> toolIssuesDetailRequest);
    List<ToolIssuesDetailResponse> findAllInIssues (Long issuesId);

}
