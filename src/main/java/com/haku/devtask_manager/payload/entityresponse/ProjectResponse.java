package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.ProjectDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {
    private Long projectId;

    private String projectName;
    private String description;
    private boolean projectType;
    private Date createdDate;
    private Date startDate;
    private Date endDate;
    private Date actualEndDate;
    private String status;
    private String projectCondition; // It will have 3 values: active, paused, canceled
    private double progress;
    private Long projectManagerId;
    private Long createrId;
    private Long taskParentId; // id của dự án cha trong dự án

    List<ProjectDetailResponse> projectDetailResponses; // danh sách nhân viên trong dự án
    List<ProjectDepartmentDetailResponse> projectDepartmentDetailResponses; // danh sách các phòng ban trong dự án

}
