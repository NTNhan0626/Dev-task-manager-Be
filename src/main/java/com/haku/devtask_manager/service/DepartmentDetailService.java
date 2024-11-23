package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.DepartmentDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.DepartmentDetailResponse;

import java.util.List;

public interface DepartmentDetailService {
    List<DepartmentDetailResponse> getAllDepartmentDetail (Long departmentId);
    DepartmentDetailResponse getDepartmentDetailById (Long departmentId, Long accountId);
    DepartmentDetailResponse createDepartmentDetail(DepartmentDetailRequest departmentDetailRequest);
    DepartmentDetailResponse updateDepartmentDetail (DepartmentDetailRequest departmentDetailRequest,Long departmentId,Long accountId);
    DepartmentDetailResponse deleteDepartmentDetail (Long departmentId);
    List<DepartmentDetailResponse> createDepartmentDetailsByDepartmentAndAccounts(Long departmentId,List<Long> accountId);
    List<DepartmentDetailResponse> updateDepartmenDetailForTranferStaff(List<Long> departmentDetailId);
    List<AccountResponse> getDepartmentDetailsByDepartmentIdandNullTimeOut(Long departmentId);

}
