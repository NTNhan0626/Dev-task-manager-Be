package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.TaskRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;

public interface TaskService {
    TaskResponse getTaskById(Long taskId);
    TaskResponse createTask (TaskRequest taskRequest, Long projectId,Long taskParentId);
    TaskResponse updateTask (TaskRequest taskRequest,Long taskId);
    TaskResponse updateTaskProgress (Long taskId, String progress);
    void deleteTask (Long taskId);
}
