package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.CategoryDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.CategoryResPonse;
import com.haku.devtask_manager.service.CategoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorydetail")
public class CategoryDetailController {
    private final CategoryDetailService categoryDetailService;

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDetailResponse>> createByCategoryIdId(
            @RequestBody CategoryDetailRequest categoryDetailRequest,
            @PathVariable(name = "categoryId") Long categoryId ){
        CategoryDetailResponse categoryDetailResponse = categoryDetailService.createCategoryDetail(categoryDetailRequest,categoryId);
        ApiResponse<CategoryDetailResponse> apiResponse = new ApiResponse<>(100,"Create CategoryDetail Success",categoryDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{categoryDetailId}")
    public ResponseEntity<ApiResponse<CategoryDetailResponse>> updateByCategoryDetail(
            @RequestBody CategoryDetailRequest categoryDetailRequest,
            @PathVariable Long categoryDetailId ){
        CategoryDetailResponse categoryDetailResponse = categoryDetailService.updateCategoryDetail(categoryDetailRequest,categoryDetailId);
        ApiResponse<CategoryDetailResponse> apiResponse = new ApiResponse<>(100,"update CategoryDetail Success",categoryDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/delete/{categoryDetailId}")
    public ResponseEntity<ApiResponse<CategoryDetailResponse>> deleteCategoryDetail(
            @PathVariable Long categoryDetailId ){
        CategoryDetailResponse categoryDetailResponse = categoryDetailService.deleteCategoryDetail(categoryDetailId);
        ApiResponse<CategoryDetailResponse> apiResponse = new ApiResponse<>(100,"delete CategoryDetail Success",categoryDetailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
