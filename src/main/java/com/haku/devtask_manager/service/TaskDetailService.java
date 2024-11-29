package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.TaskDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskDetailResponse;

import java.util.List;

public interface TaskDetailService {
    TaskDetailResponse createTaskDetail(TaskDetailRequest taskDetailRequest, Long taskId,Long accountId);
    List<TaskDetailResponse> createTaskDetailResponseList(Long taskId,List<Long> accounts,Long managerTaskId);// dùng để taoh danh sách nhân viên trong công viêc
    List<TaskDetailResponse> getTaskDetailResponseList(Long taskId);//lấy danh sách nhân viên trong công việc
}
