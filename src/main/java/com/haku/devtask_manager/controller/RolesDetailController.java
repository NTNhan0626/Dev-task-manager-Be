package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.RolesDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.RolesRequest;
import com.haku.devtask_manager.payload.entityresponse.RolesDetailResponse;
import com.haku.devtask_manager.repository.RolesDetailRepo;
import com.haku.devtask_manager.service.RolesDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolesdetail")
@RequiredArgsConstructor
public class RolesDetailController {
    private final RolesDetailService rolesDetailService;

    @GetMapping("/get/{rolesId}")
    public ResponseEntity<ApiResponse<List<RolesDetailResponse>>> getListRolesDetailByRolesId(
            @PathVariable(name = "rolesId") Long rolesId){
        List<RolesDetailResponse> rolesDetailResponses = rolesDetailService.getRolesDetailByRolesId(rolesId);
        ApiResponse<List<RolesDetailResponse>> apiResponse = new ApiResponse<>(100,"Get Roles detail by rolesid Success",rolesDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PostMapping("/add/{accountId}/{rolesId}")
    public ResponseEntity<ApiResponse<RolesDetailResponse>> addRolesDetail (
            @PathVariable(name = "accountId" )Long accountId,
            @PathVariable(name = "rolesId" )Long rolesId,
            @RequestBody RolesDetailRequest rolesDetailRequest){
        RolesDetailResponse rolesDetailResponse = rolesDetailService.addRolesDetail(rolesDetailRequest,accountId,rolesId);
        ApiResponse<RolesDetailResponse> apiResponse = new ApiResponse<>(1000,"Add Roles Detail Success",rolesDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/addlist/{departmentName}")
    public ResponseEntity<ApiResponse<List<RolesDetailResponse>>> createRolesDetailsByDepartmentNameAndAccounts(
            @PathVariable String departmentName,
            @RequestBody List<Long> accountIds
    ){
        List<RolesDetailResponse> rolesDetailResponses = rolesDetailService.createRolesDetailsByDepartmentNameAndAccounts(departmentName,accountIds);
        ApiResponse<List<RolesDetailResponse>> apiResponse = new ApiResponse<>(100,"add list rolesdetail from department success",rolesDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/update/{accountId}/{rolesId}")
    public ResponseEntity<ApiResponse<RolesDetailResponse>> updateRolesDetail(
            @PathVariable Long accountId,
            @PathVariable Long rolesId,
            @RequestBody RolesDetailRequest rolesDetailRequest){
        RolesDetailResponse rolesDetailResponse = rolesDetailService.updateRolesDetail(rolesDetailRequest,accountId,rolesId);
        ApiResponse<RolesDetailResponse> apiResponse = new ApiResponse<>(100,"update rolesdetail success",rolesDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/deletelist/{departmentName}")
    public ResponseEntity<ApiResponse<List<RolesDetailResponse>>> deleteRolesDepartmentListWhenTranferStaff(
            @PathVariable String departmentName,
            @RequestBody List<Long> accountIds
    ){
        List<RolesDetailResponse> rolesDetailResponses = rolesDetailService.deleteRolesDetailsByDepartmentNameAndAccounts(departmentName,accountIds);
        ApiResponse<List<RolesDetailResponse>> apiResponse = new ApiResponse<>(100,"delete rolesdetail when tranfer staff success",rolesDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }




}
