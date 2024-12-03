package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;

import java.util.List;

public interface ToolService {
    ToolResponse createTool (ToolRequest toolRequest);
    List<ToolResponse> findAllTool();
    ToolResponse updateTool (ToolRequest toolRequest,Long toolId);


}
