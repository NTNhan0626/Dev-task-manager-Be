package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.ToolProjectDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolProjectDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import com.haku.devtask_manager.service.ToolProjectDetailService;
import com.haku.devtask_manager.service.ToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tooldetail")
public class ToolProjectDetailController {
    private final ToolProjectDetailService toolProjectDetailService;
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<ToolProjectDetailResponse>>> findAll(

    ){
        List<ToolProjectDetailResponse> toolResponseList = toolProjectDetailService.findAllToolProjectDetails();
        ApiResponse<List<ToolProjectDetailResponse>> apiResponse = new ApiResponse<>(100,"Get All Tooldetail  Success",toolResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getall/{projectId}")
    public ResponseEntity<ApiResponse<List<ToolProjectDetailResponse>>> findAllByProject(
            @PathVariable Long projectId
    ){
        List<ToolProjectDetailResponse> toolResponseList = toolProjectDetailService.findAllByProject(projectId);
        ApiResponse<List<ToolProjectDetailResponse>> apiResponse = new ApiResponse<>(100,"Get All Tooldetail in project Success",toolResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ToolProjectDetailResponse>> createTool(
            @RequestBody ToolProjectDetailRequest toolProjectDetailRequest
            ){
        ToolProjectDetailResponse toolProjectDetailResponse = toolProjectDetailService.createToolProjectDetail(toolProjectDetailRequest);
        ApiResponse<ToolProjectDetailResponse> apiResponse = new ApiResponse<>(100,"create ToolprojectDtail Success",toolProjectDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/update/{toolProjectDetailIdId}")
    public ResponseEntity<ApiResponse<ToolProjectDetailResponse>> updateToolProjectDetail(
            @PathVariable Long toolProjectDetailIdId,
            @RequestBody ToolProjectDetailRequest toolProjectDetailRequest
    ){
        ToolProjectDetailResponse toolResponse = toolProjectDetailService.updateToolProjectDetail(toolProjectDetailRequest,toolProjectDetailIdId);
        ApiResponse<ToolProjectDetailResponse> apiResponse = new ApiResponse<>(100,"update ToolProjectDetail Success",toolResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/delete/{toolProjectDetailIdId}")
    public ResponseEntity<ApiResponse<ToolProjectDetailResponse>> deleteToolProjectDetail(
            @PathVariable Long toolProjectDetailIdId
    ){
        ToolProjectDetailResponse toolResponse = toolProjectDetailService.deleteToolProjectDetail(toolProjectDetailIdId);
        ApiResponse<ToolProjectDetailResponse> apiResponse = new ApiResponse<>(100,"delete ToolProjectDetail Success",toolResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
