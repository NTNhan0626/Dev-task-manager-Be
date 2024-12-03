package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.AccountRequest;
import com.haku.devtask_manager.payload.entityrequest.ProjectDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;
import com.haku.devtask_manager.service.ProjectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
// tạo nhân viên cho dự án liên phòng ban lúc quản lí dự án yêu cầu người thì chưa đươc duyệt status = pendingapproval
    @PostMapping("/create/interdepartmentalproject/{projectId}")
    public ResponseEntity<ApiResponse<List<ProjectDetailResponse>>> createByProjectIdAndAccountIdsandStatus (
            @PathVariable Long projectId,
            @RequestBody List<Long> accountIds
    ){
        List<ProjectDetailResponse> projectDetailRequests = projectDetailService.createByProjectIdAndAccountIdsAndStatus(projectId,accountIds,"pendingapproval");
        ApiResponse<List<ProjectDetailResponse>> apiResponse = new ApiResponse<>(100,"create interdepartmentalproject detail success",projectDetailRequests);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/get/acrequest/{projectId}")
    public ResponseEntity<ApiResponse<List<ProjectDetailResponse>>> getAllAccountRequest(
            @PathVariable Long projectId
            ){
        List<ProjectDetailResponse> projectDetailResponses = projectDetailService.getAllAccountRequest(projectId,"pendingapproval");
        ApiResponse<List<ProjectDetailResponse>> apiResponse = new ApiResponse<>(100,"get account response success",projectDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    // xử lí duyệt nhân viên vào dự án liên phòng ban
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<List<ProjectDetailResponse>>> updateProjectDetail (
            @RequestBody List<ProjectDetailRequest> projectDetailRequests
    ){
        List<ProjectDetailResponse> projectDetailResponseList = projectDetailService.updateProjectDetail(projectDetailRequests);
        ApiResponse<List<ProjectDetailResponse>> apiResponse = new ApiResponse<>(100,"approve account success",projectDetailResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
