package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.DegreeDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.DegreeRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.DegreeResponse;

import java.util.List;

public interface DegreeDetailService {
    DegreeDetailResponse createDegree(DegreeDetailRequest degreeRequest,Long degreeId,Long accountId);
    List<DegreeDetailResponse> getDegreeDetailByAccountId (Long accountId);
    List<DegreeDetailResponse> getDegreeDetailByDegreeId (Long degreeId);
    DegreeDetailResponse updateDegree(DegreeDetailRequest degreeRequest,Long degreeDetailId);
    DegreeDetailResponse deleteDegree(Long degreeId,Long accountId);
}
