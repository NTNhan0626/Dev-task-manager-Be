package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.CategoryDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryDetailResponse;

public interface CategoryDetailService {
    CategoryDetailResponse createCategoryDetail (CategoryDetailRequest categoryDetailRequest,Long categoryId);
}
