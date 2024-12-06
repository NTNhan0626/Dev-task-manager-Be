package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Issues;
import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.IssuesMapper;
import com.haku.devtask_manager.payload.entityrequest.IssuesRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.IssuesResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolIssuesDetailResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.IssuesRepo;
import com.haku.devtask_manager.repository.TaskRepo;
import com.haku.devtask_manager.service.IssuesService;
import com.haku.devtask_manager.service.ToolIssuesDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssuesServiceImpl implements IssuesService {
    private final IssuesRepo issuesRepo;
    private final AccountRepo accountRepo;
    private final TaskRepo taskRepo;
    private final IssuesMapper issuesMapper;

    private final ToolIssuesDetailService toolIssuesDetailService;
    @Override
    public List<IssuesResponse> findAllIssuesInProject(Long projectId) {
        List<Issues> issuesList = issuesRepo.findAll();
        if (issuesList.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.ISSUES_NOT_FOUND);


        return issuesList.stream()
                .filter(issues -> issues.getTask().getProject().getProjectId().equals(projectId))
                .map(issues -> {
                    IssuesResponse issuesResponse = issuesMapper.toIssuesResponse(issues);
                    issuesResponse.setTaskId(issues.getTask().getTaskId());
                    issuesResponse.setTaskName(issues.getTask().getTaskName());
                    issuesResponse.setAccountrpId(issues.getAccountrp().getAccountId());
                    issuesResponse.setAccountrpName(issues.getAccountrp().getUsername());
                    issuesResponse.setToolIssuesDetailResponses(issues.getToolIssuesDetailList().stream()
                            .map(toolIssuesDetail -> {
                                return ToolIssuesDetailResponse.builder()
                                        .toolIssuesDetailId(toolIssuesDetail.getToolIssuesDetailId())
                                        .quantity(toolIssuesDetail.getQuantity())

                                        .issuesName(toolIssuesDetail.getIssues().getIssueName())
                                        .issuesId(toolIssuesDetail.getIssues().getIssuesId())
                                        .toolId(toolIssuesDetail.getTool().getToolId())
                                        .toolName(toolIssuesDetail.getTool().getToolName())
                                        .build();
                            })
                            .collect(Collectors.toList()));

                    return  issuesResponse;
                })

                .collect(Collectors.toList());
    }

    @Override
    public List<IssuesResponse> findAllIssuesInTask(Long taskId) {
        List<Issues> issuesList = issuesRepo.findAllByTask_TaskId(taskId);
        if (issuesList.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.ISSUES_NOT_FOUND);


        return issuesList.stream()
                .map(issues -> {
                    IssuesResponse issuesResponse = issuesMapper.toIssuesResponse(issues);
                    issuesResponse.setTaskId(issues.getTask().getTaskId());
                    issuesResponse.setTaskName(issues.getTask().getTaskName());
                    issuesResponse.setAccountrpId(issues.getAccountrp().getAccountId());
                    issuesResponse.setAccountrpName(issues.getAccountrp().getUsername());
                    issuesResponse.setToolIssuesDetailResponses(issues.getToolIssuesDetailList().stream()
                            .map(toolIssuesDetail -> {
                                return ToolIssuesDetailResponse.builder()
                                        .toolIssuesDetailId(toolIssuesDetail.getToolIssuesDetailId())
                                        .quantity(toolIssuesDetail.getQuantity())

                                        .issuesName(toolIssuesDetail.getIssues().getIssueName())
                                        .issuesId(toolIssuesDetail.getIssues().getIssuesId())
                                        .toolId(toolIssuesDetail.getTool().getToolId())
                                        .toolName(toolIssuesDetail.getTool().getToolName())
                                        .build();
                            })
                            .collect(Collectors.toList()));

                    return  issuesResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public IssuesResponse createIssues(IssuesRequest issuesRequest, Long accountId, Long taskId, List<ToolIssuesDetailRequest> toolIssuesDetailRequests) {
        Account account = accountRepo.findById(accountId).orElseThrow();
        Task task = taskRepo.findById(taskId).orElseThrow();

        Issues issues = issuesMapper.toIssues(issuesRequest);
        issues.setAccountrp(account);
        issues.setTask(task);

        issuesRepo.save(issues);
        Issues issues1 = issuesRepo.findTopByOrderByIssuesIdDesc();

        toolIssuesDetailRequests.forEach(toolIssuesDetailRequest -> toolIssuesDetailRequest.setIssuesId(issues1.getIssuesId()));
        toolIssuesDetailService.createToolIssuesDetail(toolIssuesDetailRequests);
        return issuesMapper.toIssuesResponse(issues);
    }

    @Override
    public IssuesResponse updateIssues(IssuesRequest issuesRequest, Long issuesId) {
        Issues issues = issuesRepo.findById(issuesId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.ISSUES_NOT_FOUND));
       issues.setStatus(issuesRequest.getStatus());
       if (issuesRequest.getResolvedDate()!=null) issues.setResolvedDate(issuesRequest.getResolvedDate());
       issuesRepo.save(issues);
        return issuesMapper.toIssuesResponse(issues);
    }

    @Override
    public IssuesResponse deleteIssues(Long issuesId) {
        Issues issues = issuesRepo.findById(issuesId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.ISSUES_NOT_FOUND));
        issuesRepo.delete(issues);

        return issuesMapper.toIssuesResponse(issues);
    }
}
