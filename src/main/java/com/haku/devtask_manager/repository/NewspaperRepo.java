package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewspaperRepo extends JpaRepository <Newspaper,String>{
    boolean existsByName(String username);
}
