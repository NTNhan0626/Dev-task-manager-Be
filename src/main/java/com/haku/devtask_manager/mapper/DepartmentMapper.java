package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Department;
import com.haku.devtask_manager.payload.entityrequest.DepartmentRequest;
import com.haku.devtask_manager.payload.entityresponse.DepartmentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toDepartment (DepartmentRequest departmentRequest);
    DepartmentResponse toDepartmentResponse (Department department);
    List<DepartmentResponse> toDepartmentResponseList (List<Department> departmentList);
}
