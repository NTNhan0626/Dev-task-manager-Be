package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.RolesRequest;
import com.haku.devtask_manager.payload.entityresponse.RolesResponse;
import com.haku.devtask_manager.repository.RolesRepo;
import com.haku.devtask_manager.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolesController {
    private final RolesService rolesService;

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<RolesResponse>>> getRoles(){
        List<RolesResponse> rolesResponses = rolesService.getRoles();
        ApiResponse<List<RolesResponse>> apiResponse = new ApiResponse<>(100,"Get Roles Success",rolesResponses);
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/addRole")
    public ResponseEntity<ApiResponse<RolesResponse>> addRoles(@RequestBody RolesRequest rolesRequest){
        RolesResponse rolesResponse = rolesService.addRoles(rolesRequest);
        ApiResponse<RolesResponse> apiResponse = new ApiResponse<>(100,"ADD Roles Success",rolesResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
