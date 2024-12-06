package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.TaskEvaluationRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskEvaluationResponse;
import com.haku.devtask_manager.service.TaskEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/taskevaluation")
public class TaskEvaluationController {
    private final TaskEvaluationService taskEvaluationService;

    // Lấy danh sách tất cả TaskEvaluation
    @GetMapping("/get/{taskId}")
    public ResponseEntity<ApiResponse<TaskEvaluationResponse>> findTaskEvaluationByTask(
            @PathVariable Long taskId
    ) {
        TaskEvaluationResponse taskEvaluations = taskEvaluationService.getTaskEvaluationInTask(taskId);
        ApiResponse<TaskEvaluationResponse> apiResponse = new ApiResponse<>(100, "Get Task Evaluations Success", taskEvaluations);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Tạo mới một TaskEvaluation
    @PostMapping("/create/{accountId}/{taskId}")
    public ResponseEntity<ApiResponse<TaskEvaluationResponse>> createTaskEvaluation(
            @RequestBody TaskEvaluationRequest taskEvaluationRequest,
            @PathVariable Long accountId,
            @PathVariable Long taskId
    ) {
        TaskEvaluationResponse taskEvaluationResponse = taskEvaluationService.createTaskEvaluation(taskEvaluationRequest, accountId, taskId);
        ApiResponse<TaskEvaluationResponse> apiResponse = new ApiResponse<>(100, "Create TaskEvaluation Success", taskEvaluationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Cập nhật TaskEvaluation theo ID
    @PutMapping("/update/{taskEvaluationId}")
    public ResponseEntity<ApiResponse<TaskEvaluationResponse>> updateTaskEvaluation(
            @PathVariable Long taskEvaluationId,
            @RequestBody TaskEvaluationRequest taskEvaluationRequest
    ) {
        TaskEvaluationResponse taskEvaluationResponse = taskEvaluationService.updateTaskEvaluation(taskEvaluationRequest, taskEvaluationId);
        ApiResponse<TaskEvaluationResponse> apiResponse = new ApiResponse<>(100, "Update TaskEvaluation Success", taskEvaluationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Xóa TaskEvaluation theo ID
    @DeleteMapping("/delete/{taskEvaluationId}")
    public ResponseEntity<ApiResponse<TaskEvaluationResponse>> deleteTaskEvaluation(
            @PathVariable Long taskEvaluationId
    ) {
        TaskEvaluationResponse taskEvaluationResponse = taskEvaluationService.deleteTaskEvaluation(taskEvaluationId);
        ApiResponse<TaskEvaluationResponse> apiResponse = new ApiResponse<>(100, "Delete TaskEvaluation Success", taskEvaluationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
