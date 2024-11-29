package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityrequest.TaskRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;
import com.haku.devtask_manager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/getallbdp/{departmentId}") // lấy tất cả dứ án của phòng ban
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllByDepartmentIdIdAndProjectType(
            @PathVariable Long departmentId

    ){
        List<ProjectResponse> projectResponses = projectService.getAllByDepartmentIdIdAndProjectType(departmentId,true);
        ApiResponse<List<ProjectResponse>> apiResponse = new ApiResponse<>(100,"get all project bdp success",projectResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/get/{projectId}")
    public ResponseEntity<ApiResponse<ProjectResponse>>getOneByProjectId (
            @PathVariable Long projectId
    ){
        ProjectResponse projectResponse = projectService.getOneByProjectId(projectId);
        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(100,"get project success",projectResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    // lấy công việc trong dự án
    @GetMapping("/gettask/{projectId}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTaskInProject(
            @PathVariable Long projectId){
        List<TaskResponse> taskResponses = projectService.getTaskInProject(projectId);
        ApiResponse<List<TaskResponse>> apiResponse = new ApiResponse<>(100,"get task in project success",taskResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    // lấy nhân viên trong dự án
    @GetMapping("/getaccount/{projectId}")
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAccountInProject(
            @PathVariable Long projectId
    ){
        List<AccountResponse> accountResponseList = projectService.getAccountInproject(projectId);
        ApiResponse<List<AccountResponse>> apiResponse = new ApiResponse<>(100,"get account in project success",accountResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/getallbac/{accountId}") // lấy tất cả dứ án của phòng ban
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllByAccountId(
            @PathVariable Long accountId

    ){
        List<ProjectResponse> projectResponses = projectService.getAllByAccountId(accountId);
        ApiResponse<List<ProjectResponse>> apiResponse = new ApiResponse<>(100,"get all project bac success",projectResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    // lấy các dự án liên phòng ban
    @GetMapping("/get/interdepartmentalproject") // lấy tất cả dứ án của phòng ban
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllInterDepartmetalProject(
    ){
        List<ProjectResponse> projectResponses = projectService.getAllInterDepartmetalProject(false);
        ApiResponse<List<ProjectResponse>> apiResponse = new ApiResponse<>(100,"get all inter department project success",projectResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/create/{departmentId}")
    public ResponseEntity<ApiResponse<ProjectResponse>> createDepartmentProject(
            @RequestBody ProjectRequest projectRequest,
            @PathVariable Long departmentId
            ){
        ProjectResponse projectResponse = projectService.createDepartmentProject(projectRequest,departmentId );
        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(100,"create project success",projectResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/create/interdepartmentalproject")
    public ResponseEntity<ApiResponse<ProjectResponse>> createInterDepartmetalProject(
            @RequestBody ProjectRequest projectRequest
    ){
        ProjectResponse projectResponse = projectService.createInterDepartmetalProject(projectRequest);
        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(100,"create inter department project success",projectResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{projectId}")
    public ResponseEntity<ApiResponse<ProjectResponse>> updateProject (
            @PathVariable Long projectId,
            @RequestBody ProjectRequest projectRequest
    ){
        ProjectResponse projectResponse = projectService.updateProject(projectRequest,projectId);
        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(100,"update project success",projectResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
