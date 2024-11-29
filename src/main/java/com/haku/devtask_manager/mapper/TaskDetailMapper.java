package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.TaskDetail;
import com.haku.devtask_manager.payload.entityrequest.TaskDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskDetailResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskDetailMapper {
    TaskDetail toTaskDetail(TaskDetailRequest taskDetailRequest);
    TaskDetailResponse toTaskDetailResponse (TaskDetail taskDetail);
    List<TaskDetailResponse> toTaskDetailResponses (List<TaskDetail> taskDetails);
}
