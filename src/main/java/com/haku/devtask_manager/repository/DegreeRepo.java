package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepo extends JpaRepository<Degree,Long> {
    boolean existsByDegreeName (String name);
}
