package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;

import java.util.List;

public interface ProjectDetailService {
    List<ProjectDetailResponse> createByProjectIdAndAccountIds(Long projectId,List<Long> accountIds); // dùng khi thêm nhân viên vào project
}
