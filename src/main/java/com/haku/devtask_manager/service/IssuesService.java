package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.IssuesRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.IssuesResponse;

import java.util.List;

public interface IssuesService {
    List<IssuesResponse> findAllIssuesInProject(Long projectId);
    List<IssuesResponse> findAllIssuesInTask (Long taskId);
    IssuesResponse createIssues (IssuesRequest issuesRequest, Long accountId, Long taskId, List<ToolIssuesDetailRequest> toolIssuesDetailRequests);
    IssuesResponse updateIssues (IssuesRequest issuesRequest,Long issuesId);
    IssuesResponse deleteIssues (Long issuesId);

}
