package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.ProjectDepartmentDetail;
import com.haku.devtask_manager.entity.ProjectDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.ProjectMapper;
import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;
import com.haku.devtask_manager.repository.DepartmentRepo;
import com.haku.devtask_manager.repository.ProjectDepartmentDetailRepo;
import com.haku.devtask_manager.repository.ProjectDetailRepo;
import com.haku.devtask_manager.repository.ProjectRepo;
import com.haku.devtask_manager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    private final ProjectDepartmentDetailRepo projectDepartmentDetailRepo;
    private final ProjectDetailRepo projectDetailRepo;
    private final DepartmentRepo departmentRepo;
    private final ProjectMapper projectMapper;
    @Transactional
    @Override
    public ProjectResponse createDepartmentProject(ProjectRequest projectRequest, Long departmentId) {
        //khi tạo 1 dự án mới từ 1 phòng nào đó thì đồng thời phải tạo chi tiết dự án phòng ban tương ứng
        Project project = projectMapper.toProject(projectRequest);
        projectRepo.save(project);

        ProjectDepartmentDetail projectDepartmentDetail = new ProjectDepartmentDetail();
        projectDepartmentDetail.setDepartment(departmentRepo.findById(departmentId)
                .orElseThrow(
                () -> new CustomRuntimeException(ExceptionCode.DEPARTMENT_NOTEXISTS.getCode(),ExceptionCode.DEPARTMENT_NOTEXISTS.getMessage())
        ));
        projectDepartmentDetail.setProject(project);
        projectDepartmentDetail.setStatus(project.getStatus());
        projectDepartmentDetailRepo.save(projectDepartmentDetail);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProject() {
        List<Project> projects =  projectRepo.findAll();
        if(projects.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND);
        return projectMapper.toProjectResponseList(projects);
    }

    @Override
    public List<ProjectResponse> getAllByDepartmentIdIdAndProjectType(Long departmentId, boolean projectType) {
        List<ProjectDepartmentDetail> projectDepartmentDetails = projectDepartmentDetailRepo.findAllByDepartment_DepartmentId(departmentId);
        if (projectDepartmentDetails.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECTDEPARTMENTDETAIL_NOT_FOUND);
        List<Long> projectIds = projectDepartmentDetails.stream()
                .map(projectDepartmentDetail -> projectDepartmentDetail.getProject().getProjectId())
                .toList();
        List<Project> projects = projectRepo.findAllById(projectIds);
        if (projects.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND);
        return projectMapper.toProjectResponseList(projects);
    }

    @Override
    public ProjectResponse getOneByProjectId(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND));
        ProjectResponse projectResponse = projectMapper.toProjectResponse(project);

        boolean checked = projectDetailRepo.existsByProject_ProjectId(projectId);
        if(checked){
            List<ProjectDetail> projectDetails = projectDetailRepo.findAllByProject_ProjectId(projectId);
            projectResponse.setProjectDetailResponses(projectDetails.stream()
                    .map(projectDetail -> ProjectDetailResponse.builder()
                            .joinDate(projectDetail.getJoinDate())
                            .userName(projectDetail.getAccount().getUsername())
                            .status(projectDetail.getStatus())
                            .build() )
                    .collect(Collectors.toList()));
        }

        return projectResponse;
    }
}
