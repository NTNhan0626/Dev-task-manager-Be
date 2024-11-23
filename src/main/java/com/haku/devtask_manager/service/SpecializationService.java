package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.SpecializationRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationResponse;

import java.util.List;

public interface SpecializationService {
    SpecializationResponse createSpecialization (SpecializationRequest specializationRequest);
    List<SpecializationResponse> getAllSpecialization ();
    SpecializationResponse updateSpecialization (SpecializationRequest specializationRequest,Long specializationId);
    SpecializationResponse deleteSpecialization (Long specializationId);

}
