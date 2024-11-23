package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.DegreeDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeDetailResponse;
import com.haku.devtask_manager.service.DegreeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/degreedetail")
public class DegreeDetailController {
    private final DegreeDetailService degreeDetailService;

    @GetMapping("/getbac/{accountId}")
    public ResponseEntity<ApiResponse<List<DegreeDetailResponse>>> getDegreeDetailByAccountId(
            @PathVariable Long accountId
    ){
        List<DegreeDetailResponse> degreeDetailResponses = degreeDetailService.getDegreeDetailByAccountId(accountId);
        ApiResponse<List<DegreeDetailResponse>> apiResponse = new ApiResponse<>(100,"get degree by accountId success",degreeDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getbdr/{degreeId}")
    public ResponseEntity<ApiResponse<List<DegreeDetailResponse>>> getDegreeDetailByDegreeId(
            @PathVariable Long degreeId
    ){
        List<DegreeDetailResponse> degreeDetailResponses = degreeDetailService.getDegreeDetailByDegreeId(degreeId);
        ApiResponse<List<DegreeDetailResponse>> apiResponse = new ApiResponse<>(100,"get degree by degreeId success",degreeDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DegreeDetailResponse>> createDegreeDetail (
            @RequestBody DegreeDetailRequest degreeDetailRequest
            ){
        DegreeDetailResponse degreeDetailResponse = degreeDetailService.createDegree(degreeDetailRequest,degreeDetailRequest.getDegreeId(), degreeDetailRequest.getAccountId());
        ApiResponse<DegreeDetailResponse> apiResponse = new ApiResponse<>(100,"create degreedetail success",degreeDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{degreeDetailId}")
    public ResponseEntity<ApiResponse<DegreeDetailResponse>> updateDegreeDetail(
            @RequestBody DegreeDetailRequest degreeDetailRequest,
            @PathVariable Long degreeDetailId

    ){
        DegreeDetailResponse degreeDetailResponse = degreeDetailService.updateDegree(degreeDetailRequest,degreeDetailId);
        ApiResponse<DegreeDetailResponse> apiResponse = new ApiResponse<>(100,"update degreedetail success",degreeDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @DeleteMapping("/delete/{accountId}/{degreeId}")
    public ResponseEntity<ApiResponse<DegreeDetailResponse>> deleteDegreeDetail(
            @PathVariable Long degreeId,
            @PathVariable Long accountId
    ){
        DegreeDetailResponse degreeDetailResponse = degreeDetailService.deleteDegree(degreeId,accountId);
        ApiResponse<DegreeDetailResponse> apiResponse = new ApiResponse<>(100,"delete dgreedetail success",degreeDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
