package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.CategoryRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryResPonse;

import java.util.List;

public interface CategoryService {

    CategoryResPonse addCategory (CategoryRequest categoryRequest);

    CategoryResPonse getCategoryById (Long categoryId);

    CategoryResPonse updateCategory (CategoryRequest categoryRequest, Long categoryId);

    CategoryResPonse deleteCategory (Long categoryId);

    List<CategoryResPonse> getAllCategory();

}
