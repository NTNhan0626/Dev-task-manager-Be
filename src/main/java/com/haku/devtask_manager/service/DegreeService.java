package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.DegreeRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeResponse;

import java.util.List;

public interface DegreeService {
    DegreeResponse createDegree(DegreeRequest degreeRequest);
    List<DegreeResponse> getAllDegree ();
    DegreeResponse updateDegree(DegreeRequest degreeRequest,Long degreeId);
    DegreeResponse deleteDegree(Long degreeId);
}
