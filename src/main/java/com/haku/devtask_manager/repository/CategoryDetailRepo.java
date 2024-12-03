package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Category;
import com.haku.devtask_manager.entity.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDetailRepo extends JpaRepository<CategoryDetail,Long> {
    List<CategoryDetail> findAllByCategory_CategoryId(Long categoryId);

}
