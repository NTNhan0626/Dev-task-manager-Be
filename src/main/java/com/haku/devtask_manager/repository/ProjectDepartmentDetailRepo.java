package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.ProjectDepartmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDepartmentDetailRepo extends JpaRepository<ProjectDepartmentDetail,Long> {
    List<ProjectDepartmentDetail> findAllByDepartment_DepartmentId(Long departmentd);
}
