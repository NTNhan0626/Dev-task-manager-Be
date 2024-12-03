package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.entity.ToolProjectDetail;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.ToolProjectDetailMapper;
import com.haku.devtask_manager.payload.entityrequest.ToolProjectDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolProjectDetailResponse;
import com.haku.devtask_manager.repository.ProjectRepo;
import com.haku.devtask_manager.repository.ToolProjectDetailRepo;
import com.haku.devtask_manager.repository.ToolRepo;
import com.haku.devtask_manager.service.ToolProjectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolProjectDetailServiceImpl implements ToolProjectDetailService {
    private final ToolProjectDetailRepo toolProjectDetailRepo;
    private final ToolRepo toolRepo;
    private final ProjectRepo projectRepo;
    private final ToolProjectDetailMapper toolProjectDetailMapper;

    @Override
    public List<ToolProjectDetailResponse> findAllToolProjectDetails() {
        List<ToolProjectDetail> toolProjectDetails = toolProjectDetailRepo.findAll();
        if (toolProjectDetails.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND);

        return toolProjectDetails.stream()
                .map(toolProjectDetail -> {
                    ToolProjectDetailResponse toolProjectDetailResponse = toolProjectDetailMapper.toToolProjectDetailResponse(toolProjectDetail);
                    toolProjectDetailResponse.setProjectName(toolProjectDetail.getProject().getProjectName());
                    toolProjectDetailResponse.setProjectId(toolProjectDetail.getProject().getProjectId());
                    toolProjectDetailResponse.setToolName(toolProjectDetail.getTool().getToolName());
                    toolProjectDetailResponse.setToolId(toolProjectDetail.getTool().getToolId());
                    return toolProjectDetailResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ToolProjectDetailResponse> findAllByProject(Long projectId) {
        List<ToolProjectDetail> toolProjectDetails = toolProjectDetailRepo.findAllByProject_ProjectId(projectId);
        if (toolProjectDetails.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND);

        return toolProjectDetails.stream()
                .map(toolProjectDetail -> {
                    ToolProjectDetailResponse toolProjectDetailResponse = toolProjectDetailMapper.toToolProjectDetailResponse(toolProjectDetail);
                    toolProjectDetailResponse.setProjectName(toolProjectDetail.getProject().getProjectName());
                    toolProjectDetailResponse.setProjectId(toolProjectDetail.getProject().getProjectId());
                    toolProjectDetailResponse.setToolName(toolProjectDetail.getTool().getToolName());
                    toolProjectDetailResponse.setToolId(toolProjectDetail.getTool().getToolId());
                    return toolProjectDetailResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ToolProjectDetailResponse createToolProjectDetail(ToolProjectDetailRequest toolProjectDetailRequest) {
        Tool tool = toolRepo.findById(toolProjectDetailRequest.getToolId()).orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND));
        Project project = projectRepo.findById(toolProjectDetailRequest.getProjectId()).orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND)) ;

        ToolProjectDetail toolProjectDetail = toolProjectDetailMapper.toToolProjectDetail(toolProjectDetailRequest);
        toolProjectDetail.setProject(project);
        toolProjectDetail.setTool(tool);
        toolProjectDetailRepo.save(toolProjectDetail);
        return toolProjectDetailMapper.toToolProjectDetailResponse(toolProjectDetail);
    }

    @Override
    public ToolProjectDetailResponse updateToolProjectDetail(ToolProjectDetailRequest toolProjectDetailRequest, Long toolProjectDetailId) {
        ToolProjectDetail toolProjectDetail = toolProjectDetailRepo.findById(toolProjectDetailId).orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND));

        toolProjectDetail.setStatus(toolProjectDetailRequest.getStatus());
        // trường hợp trả công cụ thì sẽ có ngày trả
        if (toolProjectDetailRequest.getReturnDate() != null)toolProjectDetail.setReturnDate(toolProjectDetailRequest.getReturnDate());
        //trường hợp duyệt cho mượn
        if (toolProjectDetailRequest.getAllocatedDate() != null)toolProjectDetail.setAllocatedDate(toolProjectDetailRequest.getAllocatedDate());
        toolProjectDetailRepo.save(toolProjectDetail);
        return toolProjectDetailMapper.toToolProjectDetailResponse(toolProjectDetail);
    }

    @Override
    public ToolProjectDetailResponse deleteToolProjectDetail(Long toolProjectDetailId) {
        ToolProjectDetail toolProjectDetail = toolProjectDetailRepo.findById(toolProjectDetailId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND));
        ToolProjectDetailResponse projectDetailResponse = toolProjectDetailMapper.toToolProjectDetailResponse(toolProjectDetail);
        toolProjectDetailRepo.delete(toolProjectDetail);
        return projectDetailResponse;
    }
}
