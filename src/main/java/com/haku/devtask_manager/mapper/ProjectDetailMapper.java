package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.ProjectDetail;
import com.haku.devtask_manager.payload.entityrequest.ProjectDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectDetailMapper {
    ProjectDetail toProjectDetail(ProjectDetailRequest projectDetailRequest);
    ProjectDetailResponse toProjectDetailResponse (ProjectDetail projectDetail);
    List<ProjectDetailResponse> toProjectDetailResponseList (List<ProjectDetail> projectDetails);
}
