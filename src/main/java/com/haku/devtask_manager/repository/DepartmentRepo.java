package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository <Department,Long>{
    boolean existsByDepartmentName(String departmentName);


}
