package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.SpecializationRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationResponse;
import com.haku.devtask_manager.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specialization")
public class SpecializationController {
    private final SpecializationService specializationService;

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<SpecializationResponse>>> getAllSpecialization(){
        List<SpecializationResponse> specializationResponses = specializationService.getAllSpecialization();
        ApiResponse<List<SpecializationResponse>> apiResponse = new ApiResponse<>(100,"get all specialization success",specializationResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SpecializationResponse>> createSpecialization(
            @RequestBody SpecializationRequest specializationRequest
            ){
        SpecializationResponse specializationResponse = specializationService.createSpecialization(specializationRequest);
        ApiResponse<SpecializationResponse> apiResponse = new ApiResponse<>(100,"create specialization success",specializationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{specializationId}")
    public ResponseEntity<ApiResponse<SpecializationResponse>> updateSpecialization (
            @PathVariable Long specializationId,
            @RequestBody SpecializationRequest specializationRequest
    ){
        SpecializationResponse specializationResponse = specializationService.updateSpecialization(specializationRequest,specializationId);
        ApiResponse<SpecializationResponse> apiResponse = new ApiResponse<>(100,"update specialization success",specializationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/delete/{specializationId}")
    public ResponseEntity<ApiResponse<SpecializationResponse>> deleteSpecialization (
            @PathVariable Long specializationId
    ){
        SpecializationResponse specializationResponse = specializationService.deleteSpecialization(specializationId);
        ApiResponse<SpecializationResponse> apiResponse = new ApiResponse<>(100,"delete specialization success",specializationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
