package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectRequest projectRequest);
    ProjectResponse toProjectResponse (Project project);
    List<ProjectResponse> toProjectResponseList (List<Project> projects);
}
