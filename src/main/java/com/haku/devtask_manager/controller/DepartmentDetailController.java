package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.DepartmentDetailResponse;
import com.haku.devtask_manager.service.DepartmentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departmentdetail")
public class DepartmentDetailController {
    private final DepartmentDetailService departmentDetailService;
    @GetMapping("/get/{departmentId}")
    public ResponseEntity<ApiResponse<List<DepartmentDetailResponse>>> getDepartmentDetailByDepartmentId(
            @PathVariable Long departmentId){
        List<DepartmentDetailResponse> departmentDetailResponses = departmentDetailService.getAllDepartmentDetail(departmentId);
        ApiResponse<List<DepartmentDetailResponse>> apiResponse = new ApiResponse<>(100,"get departmentdetails Success",departmentDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getac/{departmentId}")
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getDepartmentDetailsByDepartmentIdandNullTimeOut(
            @PathVariable Long departmentId
    ){
        List<AccountResponse> accountResponseList = departmentDetailService.getDepartmentDetailsByDepartmentIdandNullTimeOut(departmentId);
        ApiResponse<List<AccountResponse>> apiResponse = new ApiResponse<>(100,"get account in department success",accountResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("add/{departmentId}")
    public ResponseEntity<ApiResponse<List<DepartmentDetailResponse>>> createDepartmentDetailsByDepartmentAndAccounts(
            @PathVariable Long departmentId,
            @RequestBody List<Long> accountIds
    ){
        List<DepartmentDetailResponse> departmentDetailResponses = departmentDetailService.createDepartmentDetailsByDepartmentAndAccounts(departmentId,accountIds);
        ApiResponse<List<DepartmentDetailResponse>> apiResponse = new ApiResponse<>(100,"add list department detail success",departmentDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("update")
    public ResponseEntity<ApiResponse<List<DepartmentDetailResponse>>> updateDepartmentDetailTranferStaff(
            @RequestBody List<Long> departmentDetailId
    ){
        List<DepartmentDetailResponse> departmentDetailResponses = departmentDetailService.updateDepartmenDetailForTranferStaff(departmentDetailId);
        ApiResponse<List<DepartmentDetailResponse>> apiResponse = new ApiResponse<>(100,"update departmentdetail success",departmentDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
