package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Issues;
import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.payload.entityrequest.IssuesRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.IssuesResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IssuesMapper {
    Issues toIssues(IssuesRequest issuesRequest);
    IssuesResponse toIssuesResponse (Issues  issues);
    List<IssuesResponse> toolResponseList (List<Issues> issuesList);
}
