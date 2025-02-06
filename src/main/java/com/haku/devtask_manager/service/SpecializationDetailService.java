package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.SpecializationDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.SpecializationDetailResponse;

import java.util.List;

public interface SpecializationDetailService {
    SpecializationDetailResponse createSpecializationDetail(SpecializationDetailRequest request);
    SpecializationDetailResponse updateSpecializationDetail(Long id, SpecializationDetailRequest request);
    List<SpecializationDetailResponse> getSpecializationDetailByAccountId (Long accountId);
    List<SpecializationDetailResponse> getSpecializationDetailBySpecializationId (Long specializationId);
    SpecializationDetailResponse  deleteSpecializationDetail(Long id);
    List<SpecializationDetailResponse> getAllSpecializationDetails();
}
