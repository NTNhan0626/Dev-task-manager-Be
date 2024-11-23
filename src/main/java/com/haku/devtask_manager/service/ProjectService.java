package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createDepartmentProject(ProjectRequest projectRequest,Long departmentId);
    List<ProjectResponse> getAllProject();
    //lấy tất cả các dự án thuộc phòng ban ở đây dự án phòng ban thì có mã ng quản lí và trạng thái = 0
    List<ProjectResponse> getAllByDepartmentIdIdAndProjectType(Long departmentId,boolean projectType);
    // lấy 1 cái project nào đó
    ProjectResponse getOneByProjectId(Long projectId);
}
