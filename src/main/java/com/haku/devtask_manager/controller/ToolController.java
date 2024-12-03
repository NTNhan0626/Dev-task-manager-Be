package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryResPonse;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import com.haku.devtask_manager.service.ToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tool")
public class ToolController {
    private final ToolService toolService;
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<ToolResponse>>> findAllTool(){
        List<ToolResponse> toolResponseList = toolService.findAllTool();
        ApiResponse<List<ToolResponse>> apiResponse = new ApiResponse<>(100,"Get All Tool Success",toolResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ToolResponse>> createTool(
            @RequestBody ToolRequest toolRequest
            ){
        ToolResponse toolResponse = toolService.createTool(toolRequest);
        ApiResponse<ToolResponse> apiResponse = new ApiResponse<>(100,"create Tool Success",toolResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/update/{toolId}")
    public ResponseEntity<ApiResponse<ToolResponse>> updateTool(
            @PathVariable Long toolId,
            @RequestBody ToolRequest toolRequest
    ){
        ToolResponse toolResponse = toolService.updateTool(toolRequest,toolId);
        ApiResponse<ToolResponse> apiResponse = new ApiResponse<>(100,"update Tool Success",toolResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
