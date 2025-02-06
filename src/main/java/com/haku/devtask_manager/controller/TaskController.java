package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.TaskRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;
import com.haku.devtask_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/get/{taskId}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTaskById(
            @PathVariable Long taskId
    ){
        List<TaskResponse> taskResponses = new ArrayList<>();
        TaskResponse taskResponse = taskService.getTaskById(taskId);
        taskResponses.add(taskResponse);
        ApiResponse<List<TaskResponse>> apiResponse = new ApiResponse<>(100,"get task success",taskResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create/{projectId}")
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(
            @PathVariable Long projectId,

            @RequestBody TaskRequest taskRequest
            ){
        TaskResponse taskResponse = taskService.createTask(taskRequest,projectId,taskRequest.getTaskParentId());
        ApiResponse<TaskResponse> apiResponse = new ApiResponse<>(100,"create task success",taskResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{taskId}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> updateTask (
            @PathVariable Long taskId,
            @RequestBody TaskRequest taskRequest
    ){
        List<TaskResponse> taskResponses = new ArrayList<>();
        TaskResponse taskResponse = taskService.updateTask(taskRequest,taskId);
        taskResponses.add(taskResponse);
        ApiResponse<List<TaskResponse>> apiResponse = new ApiResponse<>(100,"update task success",taskResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/progress/{taskId}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> updateTaskProgress (
            @PathVariable Long taskId,
            @RequestBody String progress
    ){
        List<TaskResponse> taskResponses = new ArrayList<>();
        TaskResponse taskResponse = taskService.updateTaskProgress(taskId,progress);
        taskResponses.add(taskResponse);
        ApiResponse<List<TaskResponse>> apiResponse = new ApiResponse<>(100,"update task progress success",taskResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<ApiResponse<String>> deleteTask (
            @PathVariable Long taskId

    ){
        List<TaskResponse> taskResponses = new ArrayList<>();
        taskService.deleteTask(taskId);

        ApiResponse<String> apiResponse = new ApiResponse<>(100,"delete task progress success","delete task");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
