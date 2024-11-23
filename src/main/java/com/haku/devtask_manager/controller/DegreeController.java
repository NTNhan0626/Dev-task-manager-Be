package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.DegreeRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeResponse;
import com.haku.devtask_manager.service.DegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/degree")
public class DegreeController {
    private final DegreeService degreeService;

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<DegreeResponse>>> getAllDegree(){
        List<DegreeResponse> degreeResponses = degreeService.getAllDegree();
        ApiResponse<List<DegreeResponse>> apiResponse = new ApiResponse<>(100,"get add degree success",degreeResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DegreeResponse>> createDegree(
            @RequestBody DegreeRequest degreeRequest
            ){
        DegreeResponse degreeResponse = degreeService.createDegree(degreeRequest);
        ApiResponse<DegreeResponse> apiResponse = new ApiResponse<>(100,"add degree success",degreeResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("delete/{degreeId}")
    public ResponseEntity<ApiResponse<DegreeResponse>> deleteDegree(
            @PathVariable Long degreeId
    ){
        DegreeResponse degreeResponse = degreeService.deleteDegree(degreeId);
        ApiResponse<DegreeResponse> apiResponse = new ApiResponse<>(100,"delete degree success",degreeResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
