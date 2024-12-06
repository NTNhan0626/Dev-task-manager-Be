package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Issues;
import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.entity.ToolIssuesDetail;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.payload.entityrequest.ToolIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolIssuesDetailResponse;
import com.haku.devtask_manager.repository.IssuesRepo;
import com.haku.devtask_manager.repository.ToolIssuesDetailRepo;
import com.haku.devtask_manager.repository.ToolRepo;
import com.haku.devtask_manager.service.ToolIssuesDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolIssuesDetailServiceImpl implements ToolIssuesDetailService {
    private final ToolIssuesDetailRepo toolIssuesDetailRepo;
    private final ToolRepo toolRepo;
    private final IssuesRepo issuesRepo;

    @Override
    public List<ToolIssuesDetailResponse> createToolIssuesDetail(List<ToolIssuesDetailRequest> toolIssuesDetailRequests) {
        toolIssuesDetailRequests.forEach(toolIssuesDetailRequest -> {
            Tool tool = toolRepo.findById(toolIssuesDetailRequest.getToolId()).orElseThrow();
            Issues issues = issuesRepo.findById(toolIssuesDetailRequest.getIssuesId()).orElseThrow();

            ToolIssuesDetail toolIssuesDetail = new ToolIssuesDetail();
            toolIssuesDetail.setQuantity(toolIssuesDetailRequest.getQuantity());
            toolIssuesDetail.setTool(tool);
            toolIssuesDetail.setIssues(issues);

            toolIssuesDetailRepo.save(toolIssuesDetail);
        });

        return toolIssuesDetailRequests.stream()
                .map(toolIssuesDetailRequest -> ToolIssuesDetailResponse.builder()
                        .issuesId(toolIssuesDetailRequest.getIssuesId())
                        .toolId(toolIssuesDetailRequest.getIssuesId())
                        .quantity(toolIssuesDetailRequest.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ToolIssuesDetailResponse> findAllInIssues(Long issuesId) {
        List<ToolIssuesDetail> toolIssuesDetailList = toolIssuesDetailRepo.findAllByIssues_IssuesId(issuesId);
        if (toolIssuesDetailList.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.ISSUES_DETAIL_NOT_FOUND);
        }

        return toolIssuesDetailList.stream()
                .map(toolIssuesDetail -> ToolIssuesDetailResponse.builder()
                        .toolIssuesDetailId(toolIssuesDetail.getToolIssuesDetailId())
                        .quantity(toolIssuesDetail.getQuantity())

                        .issuesName(toolIssuesDetail.getIssues().getIssueName())
                        .issuesId(toolIssuesDetail.getIssues().getIssuesId())
                        .toolName(toolIssuesDetail.getTool().getToolName())
                        .toolId(toolIssuesDetail.getTool().getToolId())
                        .build())
                .collect(Collectors.toList());
    }
}
