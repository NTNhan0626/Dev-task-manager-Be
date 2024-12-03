package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Category;
import com.haku.devtask_manager.entity.CategoryDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.payload.entityrequest.CategoryDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryDetailResponse;
import com.haku.devtask_manager.repository.CategoryDetailRepo;
import com.haku.devtask_manager.repository.CategoryRepo;
import com.haku.devtask_manager.service.CategoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDetailServiceImpl implements CategoryDetailService {
    private final CategoryDetailRepo categoryDetailRepo;
    private final CategoryRepo categoryRepo;
    @Override
    public CategoryDetailResponse createCategoryDetail(CategoryDetailRequest categoryDetailRequest, Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CustomRuntimeException(ExceptionCode.CATEGORY_NOTEXISTS.getCode(),ExceptionCode.CATEGORY_NOTEXISTS.getMessage()));
        CategoryDetail categoryDetail = new CategoryDetail();
        categoryDetail.setCategory(category);
        categoryDetail.setCategoryDetailName(categoryDetailRequest.getCategoryDetailName());
        categoryDetailRepo.save(categoryDetail);
        return CategoryDetailResponse.builder()
                .categoryDetailName(categoryDetail.getCategoryDetailName())
                .categoryDetailId(categoryDetail.getCategoryDetailId())
                .categoryName(category.getCategoryName())
                .categoryId(categoryId)
                .build();
    }
}
