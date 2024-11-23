package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Category;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.mapper.CategoryMapper;
import com.haku.devtask_manager.payload.entityrequest.CategoryRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryResPonse;
import com.haku.devtask_manager.repository.CategoryRepo;
import com.haku.devtask_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResPonse addCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        categoryRepo.save(category);
        return categoryMapper.toCategoryResPonse(category);
    }

    @Override
    public CategoryResPonse getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getCode()));

        return categoryMapper.toCategoryResPonse(category);
    }

    @Override
    public CategoryResPonse updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        return null;
    }

    @Override
    public CategoryResPonse deleteCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryResPonse> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        if (categoryList.isEmpty()){
            throw new CustomRuntimeException(ExceptionCode.CATEGORY_NOTEXISTS.getCode(),ExceptionCode.CATEGORY_NOTEXISTS.getMessage());
        }
        return categoryMapper.toCategoryResPonseList(categoryList);
    }
}
