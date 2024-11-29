package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;
import com.haku.devtask_manager.service.ProjectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projectdetail")
public class ProjectDetailController {
    private final ProjectDetailService projectDetailService;

    @PostMapping("/create/{projectId}")
    public ResponseEntity<ApiResponse<List<ProjectDetailResponse>>> createByProjectIdAndAccountIds (
            @PathVariable Long projectId,
            @RequestBody List<Long> accountIds
    ){
        List<ProjectDetailResponse> projectDetailRequests = projectDetailService.createByProjectIdAndAccountIds(projectId,accountIds);
        ApiResponse<List<ProjectDetailResponse>> apiResponse = new ApiResponse<>(100,"create project detail success",projectDetailRequests);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
