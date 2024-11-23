package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.DepartmentRequest;
import com.haku.devtask_manager.payload.entityresponse.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartment ();
    DepartmentResponse getDepartmentById (Long departmentId);
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse updateDepartment (DepartmentRequest departmentRequest,Long departmentId);
    DepartmentResponse deleteDepartment (Long departmentId);


}
