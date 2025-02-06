package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Category;
import com.haku.devtask_manager.entity.CategoryDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.exception.ExceptionCodev2;
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

    @Override
    public CategoryDetailResponse updateCategoryDetail(CategoryDetailRequest categoryDetailRequest, Long categoryDetailId) {
        CategoryDetail categoryDetail = categoryDetailRepo.findById( categoryDetailId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.CATEGORYDETAIL_NOT_FOUND));
        categoryDetail.setCategoryDetailName(categoryDetailRequest.getCategoryDetailName());
        categoryDetailRepo.save(categoryDetail);
        return CategoryDetailResponse.builder()
                .categoryDetailId(categoryDetail.getCategoryDetailId())
                .categoryDetailName(categoryDetail.getCategoryDetailName())
                .build();
    }

    @Override
    public CategoryDetailResponse deleteCategoryDetail(Long categoryDetailId) {
        CategoryDetail categoryDetail = categoryDetailRepo.findById( categoryDetailId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.CATEGORYDETAIL_NOT_FOUND));
        categoryDetailRepo.delete(categoryDetail);
        return CategoryDetailResponse.builder()
                .categoryDetailId(categoryDetail.getCategoryDetailId())
                .categoryDetailName(categoryDetail.getCategoryDetailName())
                .build();
    }
}
