package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}
