package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityresponse.TaskDetailResponse;
import com.haku.devtask_manager.service.TaskDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/taskdetail")
public class TaskDetailController {
    private final TaskDetailService taskDetailService;
    @GetMapping("/get/{taskId}")
    public ResponseEntity<ApiResponse<List<TaskDetailResponse>>> getTaskDetailResponseList(
            @PathVariable Long taskId
    ){
        List<TaskDetailResponse> taskDetailResponses = taskDetailService.getTaskDetailResponseList(taskId);
        ApiResponse<List<TaskDetailResponse>> apiResponse = new ApiResponse<>(100,"get taskdetail by taskId success",taskDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create/{taskId}/{managerTaskId}")
    public ResponseEntity<ApiResponse<List<TaskDetailResponse>>> createTaskDetailResponseList(
            @PathVariable Long taskId,
            @PathVariable Long managerTaskId,
            @RequestBody List<Long> accountIds

    ){
        List<TaskDetailResponse> taskDetailResponses = taskDetailService.createTaskDetailResponseList(taskId,accountIds,managerTaskId);
        ApiResponse<List<TaskDetailResponse>> apiResponse = new ApiResponse<>(100,"create list task response success",taskDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/delete/{taskdetailId}")
    public ResponseEntity<ApiResponse<List<TaskDetailResponse>>> delete(
            @PathVariable Long taskdetailId

    ){
        taskDetailService.deleteTaskDetail(taskdetailId);
        ApiResponse<List<TaskDetailResponse>> apiResponse = new ApiResponse<>(100,"create list task response success",null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
