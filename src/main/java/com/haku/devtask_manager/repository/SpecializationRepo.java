package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepo extends JpaRepository<Specialization,Long> {
    boolean existsBySpecializationName(String name);
}
