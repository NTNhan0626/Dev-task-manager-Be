package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.CategoryRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryResPonse;
import com.haku.devtask_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

//    @PreAuthorize("")
    //@PostAuthorize()
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<CategoryResPonse>>> getAllCategory(){
        List<CategoryResPonse> categoryResPonseList = categoryService.getAllCategory();
        ApiResponse<List<CategoryResPonse>> apiResponse = new ApiResponse<>(100,"Get All Category Success",categoryResPonseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResPonse>> getCategoryById(@PathVariable(name = "categoryId") Long categoryId ){
        CategoryResPonse categoryResPonse = categoryService.getCategoryById(categoryId);
        ApiResponse<CategoryResPonse> apiResponse = new ApiResponse<>(100,"Get Category Success",categoryResPonse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CategoryResPonse>> addCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryResPonse categoryResPonse = categoryService.addCategory(categoryRequest);
        ApiResponse<CategoryResPonse> apiResponse = new ApiResponse<>(100,"Add Category Success",categoryResPonse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }



}
