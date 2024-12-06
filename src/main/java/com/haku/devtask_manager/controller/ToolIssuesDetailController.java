package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.IssuesRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolIssuesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.IssuesResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolIssuesDetailResponse;
import com.haku.devtask_manager.service.IssuesService;
import com.haku.devtask_manager.service.ToolIssuesDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/toolissuesdetail")
public class ToolIssuesDetailController {
    private final ToolIssuesDetailService toolIssuesDetailService;
    @GetMapping("/getall/issues/{issuesId}")
    private ResponseEntity<ApiResponse<List<ToolIssuesDetailResponse>>> findAllInIssues(
            @PathVariable Long issuesId
    ){
        List<ToolIssuesDetailResponse> issuesResponses = toolIssuesDetailService.findAllInIssues(issuesId);
        ApiResponse<List<ToolIssuesDetailResponse>> apiResponse = new ApiResponse<>(100,"get all tool issues detail in issues success",issuesResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    private ResponseEntity<ApiResponse<List<ToolIssuesDetailResponse>>> createToolIssuesDetail(
            @RequestBody List<ToolIssuesDetailRequest> toolIssuesDetailRequests


    ){
        List<ToolIssuesDetailResponse> toolIssuesDetailResponse = toolIssuesDetailService.createToolIssuesDetail(toolIssuesDetailRequests);
        ApiResponse<List<ToolIssuesDetailResponse>> apiResponse = new ApiResponse<>(100,"create tool issues detail success",toolIssuesDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
