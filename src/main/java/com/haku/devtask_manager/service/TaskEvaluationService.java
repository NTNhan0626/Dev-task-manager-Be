package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.TaskEvaluationRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskEvaluationResponse;

public interface TaskEvaluationService {
    TaskEvaluationResponse createTaskEvaluation(TaskEvaluationRequest taskEvaluationRequest,Long accountId,Long taskId);
    TaskEvaluationResponse getTaskEvaluationInTask(Long taskId);
    TaskEvaluationResponse updateTaskEvaluation(TaskEvaluationRequest taskEvaluationRequest,Long taskEvaluationId);
    TaskEvaluationResponse deleteTaskEvaluation(Long taskEvaluationId);
}
