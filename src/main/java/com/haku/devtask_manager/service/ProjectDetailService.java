package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.ProjectDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;

import java.util.List;

public interface ProjectDetailService {
    List<ProjectDetailResponse> createByProjectIdAndAccountIds(Long projectId,List<Long> accountIds); // dùng khi thêm nhân viên vào project
    //dùng khi quanr lí dự án yêu cầu người từ các phòng ban có tham gia
    List<ProjectDetailResponse> createByProjectIdAndAccountIdsAndStatus(Long projectId,List<Long> accountIds,String status);
    List<ProjectDetailResponse> getAllAccountRequest(Long projectId,String status );
    List<ProjectDetailResponse> updateProjectDetail (List<ProjectDetailRequest> projectDetailRequests);
    ProjectDetailResponse deletedProjectDetail(Long projectId,Long accountId);
}
