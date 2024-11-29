package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.LogWorkRequest;
import com.haku.devtask_manager.payload.entityresponse.LogWorkResponse;
import com.haku.devtask_manager.service.LogworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logwork")
public class LogworkController {
    private final LogworkService logworkService;

    @GetMapping("/get/{taskId}")
    public ResponseEntity<ApiResponse<List<LogWorkResponse>>> getLogworkByTaskId(
            @PathVariable Long taskId
    ){
        List<LogWorkResponse> logWorkResponses = logworkService.getLogworkByTaskId(taskId);
        ApiResponse<List<LogWorkResponse>> apiResponse = new ApiResponse<>(100,"get logwork success",logWorkResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create/{taskId}")
    public ResponseEntity<ApiResponse<LogWorkResponse>> createLogwork(
            @PathVariable Long taskId,
            @RequestBody LogWorkRequest logWorkRequest
            ){
        LogWorkResponse logWorkResponse = logworkService.createLogwork(logWorkRequest,taskId);
        ApiResponse<LogWorkResponse> apiResponse = new ApiResponse<>(100,"create logwork success",logWorkResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/updatestatus/{logworkId}")
    public ResponseEntity<ApiResponse<LogWorkResponse>> updateLogworkStatus(
            @PathVariable Long logworkId,
            @RequestBody String status
    ){
        LogWorkResponse logWorkResponse = logworkService.updateLogworkStatus(logworkId,status);
        ApiResponse<LogWorkResponse> apiResponse = new ApiResponse<>(100,"update logwork status success",logWorkResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
