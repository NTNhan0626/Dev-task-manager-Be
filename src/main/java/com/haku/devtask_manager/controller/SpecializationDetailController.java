package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.SpecializationDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationDetailResponse;
import com.haku.devtask_manager.service.SpecializationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specializationdetail")
@RequiredArgsConstructor
public class SpecializationDetailController {

    private final SpecializationDetailService specializationDetailService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<SpecializationDetailResponse>> createSpecializationDetail(@RequestBody SpecializationDetailRequest request) {
        SpecializationDetailResponse response = specializationDetailService.createSpecializationDetail(request);
        ApiResponse<SpecializationDetailResponse> apiResponse = new ApiResponse<>(100,"create specialization success",response);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SpecializationDetailResponse> updateSpecializationDetail(@PathVariable Long id, @RequestBody SpecializationDetailRequest request) {
        SpecializationDetailResponse response = specializationDetailService.updateSpecializationDetail(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getbac/{accountId}")
    public ResponseEntity<ApiResponse<List<SpecializationDetailResponse>>> getSpecializationDetailByAccountId(@PathVariable Long accountId) {
        List<SpecializationDetailResponse> specializationDetailResponses = specializationDetailService.getSpecializationDetailByAccountId(accountId);
        ApiResponse<List<SpecializationDetailResponse>> apiResponse = new ApiResponse<>(100,"get specializationdetail bac success",specializationDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("getbsp/{specializationId}")
    public ResponseEntity<ApiResponse<List<SpecializationDetailResponse>>> getSpecializationDetailBySpecializationId(@PathVariable Long specializationId) {
        List<SpecializationDetailResponse> specializationDetailResponses = specializationDetailService.getSpecializationDetailBySpecializationId(specializationId);
        ApiResponse<List<SpecializationDetailResponse>> apiResponse = new ApiResponse<>(100,"get specializationdetail bac success",specializationDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteSpecializationDetail(@PathVariable Long id) {
        specializationDetailService.deleteSpecializationDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<SpecializationDetailResponse>> getAllSpecializationDetails() {
        List<SpecializationDetailResponse> responses = specializationDetailService.getAllSpecializationDetails();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
