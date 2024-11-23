package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Department;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.mapper.DepartmentMapper;
import com.haku.devtask_manager.payload.entityrequest.DepartmentRequest;
import com.haku.devtask_manager.payload.entityresponse.DepartmentResponse;
import com.haku.devtask_manager.repository.DepartmentDetailRepo;
import com.haku.devtask_manager.repository.DepartmentRepo;
import com.haku.devtask_manager.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepo departmentRepo;
    private final DepartmentDetailRepo departmentDetailRepo;


    @Override
    public List<DepartmentResponse> getAllDepartment() {
        List<Department> departments = departmentRepo.findAll();
        if(departments.isEmpty()){
            return Collections.emptyList();
        }
        return departments.stream()
                .map(department -> DepartmentResponse.builder()
                        .departmentName(department.getDepartmentName())
                        .departmentId(department.getDepartmentId())
                        .createdDate(department.getCreatedDate())
                        .status(department.getStatus())
                        .numberStaff(departmentDetailRepo.countByDepartmentAndTimeOutIsNull(department))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(Long departmentId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.DEPARTMENT_NOTEXISTS.getCode(),ExceptionCode.DEPARTMENT_NOTEXISTS.getMessage()));

        return departmentMapper.toDepartmentResponse(department);
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department department = departmentMapper.toDepartment(departmentRequest);
        boolean checker = departmentRepo.existsByDepartmentName(department.getDepartmentName());
        if(checker){
            throw new CustomRuntimeException(ExceptionCode.DEPARTMENT_EXISTS.getCode(),ExceptionCode.DEPARTMENT_EXISTS.getMessage());
        }

        departmentRepo.save(department);
        return departmentMapper.toDepartmentResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(DepartmentRequest departmentRequest, Long departmentId) {
        return null;
    }

    @Override
    public DepartmentResponse deleteDepartment(Long departmentId) {
        return null;
    }
}
