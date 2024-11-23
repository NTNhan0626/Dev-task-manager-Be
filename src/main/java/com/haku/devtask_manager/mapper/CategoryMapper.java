package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Category;
import com.haku.devtask_manager.payload.entityrequest.CategoryRequest;
import com.haku.devtask_manager.payload.entityresponse.CategoryResPonse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory (CategoryRequest categoryRequest);
    CategoryResPonse toCategoryResPonse (Category category);
    List<CategoryResPonse> toCategoryResPonseList (List<Category> categoryList);

}
