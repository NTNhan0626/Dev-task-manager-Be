package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.IssuesAndIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.IssuesRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.IssuesResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolProjectDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import com.haku.devtask_manager.service.IssuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssuesController {
    private final IssuesService issuesService;

    @GetMapping("/getall/project/{projectId}")
    private ResponseEntity<ApiResponse<List<IssuesResponse>>> findAllIssuesInProject(
            @PathVariable Long projectId
    ){
        List<IssuesResponse> issuesResponses = issuesService.findAllIssuesInProject(projectId);
        ApiResponse<List<IssuesResponse>> apiResponse = new ApiResponse<>(100,"get all issues in project success",issuesResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getall/task/{taskId}")
    private ResponseEntity<ApiResponse<List<IssuesResponse>>> findAllIssuesInTask(
            @PathVariable Long taskId
    ){
        List<IssuesResponse> issuesResponses = issuesService.findAllIssuesInTask(taskId);
        ApiResponse<List<IssuesResponse>> apiResponse = new ApiResponse<>(100,"get all issues in task success",issuesResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create/{accountId}/{taskId}")
    private ResponseEntity<ApiResponse<IssuesResponse>> createIssues(
            @RequestBody IssuesAndIssuesDetailRequest payload,
            @PathVariable Long accountId,
            @PathVariable Long taskId

    ){
        IssuesRequest issuesRequest  = payload.getIssuesRequest();
        List<ToolIssuesDetailRequest> toolIssuesDetailRequests =  payload.getToolIssuesDetailRequestList();
        IssuesResponse issuesResponse = issuesService.createIssues(issuesRequest,accountId,taskId, toolIssuesDetailRequests);

        ApiResponse<IssuesResponse> apiResponse = new ApiResponse<>(100,"create issues  success",issuesResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{issuesId}")
    public ResponseEntity<ApiResponse<IssuesResponse>> updateIssues(
            @PathVariable Long issuesId,
            @RequestBody IssuesRequest issuesRequest
    ){
        IssuesResponse issuesResponse = issuesService.updateIssues(issuesRequest,issuesId);
        ApiResponse<IssuesResponse> apiResponse = new ApiResponse<>(100,"update issues Success",issuesResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/delete/{issuesId}")
    public ResponseEntity<ApiResponse<IssuesResponse>> deleteToolProjectDetail(
            @PathVariable Long issuesId
    ){
        IssuesResponse issuesResponse = issuesService.deleteIssues(issuesId);
        ApiResponse<IssuesResponse> apiResponse = new ApiResponse<>(100,"delete issues Success",issuesResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
