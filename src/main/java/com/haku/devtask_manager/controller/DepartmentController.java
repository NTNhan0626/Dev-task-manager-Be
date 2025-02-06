package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.DepartmentRequest;
import com.haku.devtask_manager.payload.entityresponse.DepartmentResponse;
import com.haku.devtask_manager.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartment(){
        List<DepartmentResponse> departmentResponse = departmentService.getAllDepartment();
        ApiResponse<List<DepartmentResponse>> apiResponse = new ApiResponse<>(100,"get add Department Success",departmentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        ApiResponse<DepartmentResponse> apiResponse = new ApiResponse<>(100,"Add Department Success",departmentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{departmentId}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> updateDepartment(
            @RequestBody DepartmentRequest departmentRequest,
            @PathVariable Long departmentId
            ){
        DepartmentResponse departmentResponse = departmentService.updateDepartment(departmentRequest,departmentId);
        ApiResponse<DepartmentResponse> apiResponse = new ApiResponse<>(100,"update Department Success",departmentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> deleteDepartment(
            @PathVariable Long departmentId
    ){
        DepartmentResponse departmentResponse = departmentService.deleteDepartment(departmentId);
        ApiResponse<DepartmentResponse> apiResponse = new ApiResponse<>(100,"delete Department Success",departmentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
