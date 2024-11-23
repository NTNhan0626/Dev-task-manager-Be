package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.RolesDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.RolesRequest;
import com.haku.devtask_manager.payload.entityresponse.RolesDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.RolesResponse;

import java.util.List;


public interface RolesDetailService {
    RolesDetailResponse getRolesDetailById(Long rolesDetailId);
    RolesDetailResponse addRolesDetail(RolesDetailRequest rolesDetailRequest,Long accountId,Long rolesId);
    RolesDetailResponse updateRolesDetail(RolesDetailRequest rolesDetailRequest,Long accountId,Long rolesId);
    void deleteRolesDetail(Long rolesDetailId);
    List<RolesDetailResponse> getRolesDetailByRolesId(Long rolesId);

    List<RolesDetailResponse> createRolesDetailsByDepartmentNameAndAccounts(String departmentName,List<Long> accountId);
    List<RolesDetailResponse> deleteRolesDetailsByDepartmentNameAndAccounts(String departmentName,List<Long> accountIds);
}
