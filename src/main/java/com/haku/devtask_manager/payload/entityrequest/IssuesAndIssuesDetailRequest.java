package com.haku.devtask_manager.payload.entityrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssuesAndIssuesDetailRequest {
    private IssuesRequest issuesRequest;
    private List<ToolIssuesDetailRequest> toolIssuesDetailRequestList;
}
