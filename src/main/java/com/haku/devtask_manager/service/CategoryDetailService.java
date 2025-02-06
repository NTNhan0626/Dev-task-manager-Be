package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.CategoryDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryDetailResponse;

public interface CategoryDetailService {
    CategoryDetailResponse createCategoryDetail (CategoryDetailRequest categoryDetailRequest,Long categoryId);
    CategoryDetailResponse updateCategoryDetail (CategoryDetailRequest categoryDetailRequest,Long categoryDetailId);
    CategoryDetailResponse deleteCategoryDetail (Long categoryDetailId);

}
