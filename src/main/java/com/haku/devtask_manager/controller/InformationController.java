package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.InformationRequest;
import com.haku.devtask_manager.payload.entityresponse.InformationResponse;
import com.haku.devtask_manager.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/information")
public class InformationController {
    private final InformationService informationService;
    @GetMapping("/get/{accountId}")
    public ResponseEntity<ApiResponse<InformationResponse>> getInformationByAccountId(
            @PathVariable Long accountId
    ){
        InformationResponse informationResponse = informationService.getInformationByAccountId(accountId);
        ApiResponse<InformationResponse> apiResponse = new ApiResponse<>(100,"get Information success",informationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/update/{accountId}")
    public ResponseEntity<ApiResponse<InformationResponse>> updateInformationByAccoutId(
            @PathVariable Long accountId,
            @RequestBody InformationRequest informationRequest
    ){
        InformationResponse informationResponse = informationService.updateInformation(informationRequest,accountId);
        ApiResponse<InformationResponse> apiResponse = new ApiResponse<>(100,"update information success",informationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
