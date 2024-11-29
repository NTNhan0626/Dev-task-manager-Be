package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.payload.entityrequest.TaskRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask (TaskRequest taskRequest);
    TaskResponse toTaskResponse (Task task);
    List<TaskResponse> toTaskResponseList (List<Task> taskList);
}
