package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createDepartmentProject(ProjectRequest projectRequest,Long departmentId);
    List<ProjectResponse> getAllProject();
    //lấy tất cả các dự án thuộc phòng ban ở đây dự án phòng ban thì có mã ng quản lí và trạng thái = 0
    List<ProjectResponse> getAllByDepartmentIdIdAndProjectType(Long departmentId,boolean projectType);
    // lấy 1 cái project nào đó
    ProjectResponse getOneByProjectId(Long projectId);
    List<TaskResponse> getTaskInProject(Long projectId); // 1 dứ án chỉ có 1 stack cha chưng mà phải lấy dạng list để fe xử lí
    List<AccountResponse> getAccountInproject(Long projectId); // lấy nhân viên tròngz dự án

    List<ProjectResponse> getAllByAccountId(Long accountId); // lấy tất cả các project mà nhân viên từng tham gia
    ProjectResponse updateProject (ProjectRequest projectRequest, Long projectId);

    ProjectResponse createInterDepartmetalProject (ProjectRequest projectRequest);
    List<ProjectResponse> getAllInterDepartmetalProject (boolean status); // status = false thì là dự án liên phòng ban

    List<AccountResponse> getAllAccountsNotInProject (Long projectId, Long departmentId);
}
