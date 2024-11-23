package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Department;
import com.haku.devtask_manager.entity.DepartmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentDetailRepo extends JpaRepository<DepartmentDetail,Long> {
    int countByDepartmentAndTimeOutIsNull(Department department);
    List<DepartmentDetail> findAllByDepartment_DepartmentId(Long departmentId);
    DepartmentDetail findOneByAccountAndTimeOutIsNull(Account account);
    boolean existsByAccountAndTimeOutIsNull(Account account);
}
