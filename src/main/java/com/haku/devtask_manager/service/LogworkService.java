package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.LogWorkRequest;
import com.haku.devtask_manager.payload.entityresponse.LogWorkResponse;

import java.util.List;

public interface LogworkService {
    List<LogWorkResponse> getLogworkByTaskId(Long taskId);
    LogWorkResponse createLogwork(LogWorkRequest logWorkRequest,Long taskId);
    LogWorkResponse updateLogworkStatus(Long logworkId, String status);
}
