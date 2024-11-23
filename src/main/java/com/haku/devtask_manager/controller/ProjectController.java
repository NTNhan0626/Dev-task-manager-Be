package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectResponse;
import com.haku.devtask_manager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/{departmentId}")
    public ResponseEntity<ApiResponse<ProjectResponse>> createDepartmentProject(
            @RequestBody ProjectRequest projectRequest,
            @PathVariable Long departmentId
            ){
        ProjectResponse projectResponse = projectService.createDepartmentProject(projectRequest,departmentId );
        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(100,"create project success",projectResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
