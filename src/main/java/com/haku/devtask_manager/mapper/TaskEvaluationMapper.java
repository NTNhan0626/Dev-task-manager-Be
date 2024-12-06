package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.TaskEvaluation;
import com.haku.devtask_manager.payload.entityrequest.TaskEvaluationRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskEvaluationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskEvaluationMapper {
    TaskEvaluation toTaskEvaluation(TaskEvaluationRequest taskEvaluationRequest);
    TaskEvaluationResponse toTaskEvaluationResponse(TaskEvaluation taskEvaluation);

}
